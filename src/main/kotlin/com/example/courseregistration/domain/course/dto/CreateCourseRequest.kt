package com.example.courseregistration.domain.course.dto

data class CreateCourseRequest(
    // id는 자동 부여
    //val id :Long,
    val title:String,
    val description:String?,
    //val status:String,
    //val maxApplicants:Int,
    //val numApplicants:Int,
)