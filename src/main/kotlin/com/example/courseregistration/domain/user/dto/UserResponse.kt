package com.example.courseregistration.domain.user.dto

data class UserResponse(
    val id:Long,
    val email:String,
    // 비번은 보여주지 않는다. 해킹 위험 val pw:String,
    val nickname:String,
    val role:String
)
