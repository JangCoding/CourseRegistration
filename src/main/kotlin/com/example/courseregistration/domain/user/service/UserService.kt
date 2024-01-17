package com.example.courseregistration.domain.user.service

import com.example.courseregistration.domain.user.dto.*
import jakarta.persistence.Id

interface UserService {
    fun signUp(request:SignUpRequest):UserResponse
    fun updateUserProfile(userId: Long, request : UpdateUserProfileRequest): UserResponse
    fun login(request:LoginRequest) : LoginResponse

}