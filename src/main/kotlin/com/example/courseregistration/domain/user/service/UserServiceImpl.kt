package com.example.courseregistration.domain.user.service

import com.example.courseregistration.domain.exception.ModelNotFoundException
import com.example.courseregistration.domain.user.dto.SignUpRequest
import com.example.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.example.courseregistration.domain.user.dto.UserResponse
import com.example.courseregistration.domain.user.model.Profile
import com.example.courseregistration.domain.user.model.User
import com.example.courseregistration.domain.user.model.UserRole
import com.example.courseregistration.domain.user.model.toResponse
import com.example.courseregistration.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    // Repository 주입을 받아서 처리
    private val userRepository: UserRepository
) : UserService {
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        // TODO : 이메일 중복 확인. 중복된다면 예외처리. throw IllegalStateException
        // request를 User로 변환 후 DB로 저장, 비밀번호는 저장시 암호화

        if(userRepository.existsByEmail(request.email)){
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = request.email,
                password = request.password,
                profile = Profile(nickname = request.nickname),
                role = when(request.role){
                    UserRole.STUDENT.name -> UserRole.STUDENT
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