package com.example.courseregistration.domain.user.service

import com.example.courseregistration.domain.exception.ModelNotFoundException
import com.example.courseregistration.domain.user.dto.*
import com.example.courseregistration.domain.user.exception.InvalidCredentialException
import com.example.courseregistration.domain.user.model.Profile
import com.example.courseregistration.domain.user.model.User
import com.example.courseregistration.domain.user.model.UserRole
import com.example.courseregistration.domain.user.model.toResponse
import com.example.courseregistration.domain.user.repository.UserRepository
import com.example.courseregistration.infra.security.jwt.JwtPlugin
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    // Repository 주입을 받아서 처리
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin : JwtPlugin
) : UserService {
    override fun login(request: LoginRequest): LoginResponse {
        // email, role, password 체크
        val user = userRepository.findByEmail(request.email)
            ?:throw ModelNotFoundException("User", null)

        if (user.role.name != request.role
            || !passwordEncoder.matches(request.password, user.password ) )
                // request 는 encode 하기전. raw 이고, user 는 암호화 되어있는 상태
        {
            throw InvalidCredentialException()
        }

        //확인 다 되면 엑세스토큰 생성해서 반환하기
        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {

        // 이메일 중복 확인. 중복된다면 예외처리. throw IllegalStateException
        if(userRepository.existsByEmail(request.email)){
            throw IllegalStateException("Email is already in use")
        }

        // request를 User로 변환 후 DB로 저장, 비밀번호는 저장시 암호화
        return userRepository.save(
            User(
                email = request.email,
                // password 암호화
                password = passwordEncoder.encode( request.password) , // BCryptPasswordEncoder().encode()
                profile = Profile(nickname = request.nickname),
                role = when(request.role.uppercase()){
                    UserRole.STUDENT.name -> UserRole.STUDENT // 두가지 방법
                    "TUTOR" -> UserRole.TUTOR
                    else -> throw IllegalStateException("Invalid role")
                }
            )
        ).toResponse()
    }

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        // TODO : userId의 User 없다면 없다면 throw ModelNotFoundException
        // TODO : DB에서 userId 해당하는 User 가져와서 UpdateUserProfileRequest 기반으로 업데이트 후 DB에 저장, 결과를 UserResponse 변환 후 반환

        val user = userRepository.findByIdOrNull(userId) ?: throw   ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = request.nickname
        )

        return userRepository.save(user).toResponse()
    }
}