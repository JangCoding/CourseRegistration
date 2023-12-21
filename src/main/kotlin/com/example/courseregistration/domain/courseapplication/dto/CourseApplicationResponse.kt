package com.example.courseregistration.domain.courseapplication.dto

import com.example.courseregistration.domain.course.dto.CourseResponse
import com.example.courseregistration.domain.user.dto.UserResponse

data class CourseApplicationResponse(
    val id:Long,
    val course:CourseResponse, // JSON 하위에 다른 JSON 넣을 수 있음
    val user: UserResponse,
    val status :String
)
