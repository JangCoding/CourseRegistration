package com.example.courseregistration.domain.user.service

import com.example.courseregistration.domain.user.dto.SignUpRequest
import com.example.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.example.courseregistration.domain.user.dto.UserResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        // TODO : 이메일 중복 확인. 중복된다면 예외처리. throw IllegalStateException
        // request를 User로 변환 후 DB로 저장, 비밀번호는 저장시 암호화
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        // TODO : userId의 User 없다면 없다면 throw ModelNotFoundException
        // TODO : DB에서 userId 해당하는 User 가져와서 UpdateUserProfileRequest 기반으로 업데이트 후 DB에 저장, 결과를 UserResponse 변환 후 반환

        TODO("Not yet implemented")
    }
}