package com.example.courseregistration.domain.courseapplication.dto

data class UpdateApplicationStatusRequest (
    //승인 or 거절
    val status: String
)
