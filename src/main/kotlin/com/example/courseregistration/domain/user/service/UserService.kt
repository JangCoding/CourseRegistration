package com.example.courseregistration.domain.user.service

import com.example.courseregistration.domain.user.dto.SignUpRequest
import com.example.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.example.courseregistration.domain.user.dto.UserResponse
import jakarta.persistence.Id

interface UserService {
    fun signUp(request:SignUpRequest):UserResponse
    fun updateUserProfile(userId: Long, request : UpdateUserProfileRequest): UserResponse

}