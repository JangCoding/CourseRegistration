package com.example.courseregistration.domain.user.dto

data class SignUpRequest(
    val id:Long,
    val email:String,
    //val pw:String,
    val nickname:String,
    val role:String
)
