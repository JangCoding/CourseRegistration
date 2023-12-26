package com.example.courseregistration.domain.lecture.dto

data class LectureResponse(
    // dto 는 데이터 전달용이므로 val 사용, immutable
    val id :Long,
    val title:String,
    val videoUrl:String
)