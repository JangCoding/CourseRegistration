package com.example.courseregistration.domain.course.dto

data class CourseResponse(
    // dto 는 데이터 전달용이므로 val 사용, immutable
    val id :Long,
    val title:String,
    val description:String?,
    val status:String,
    val maxApplicants:Int,
    val numApplicants:Int,
)
//
//fun main(){
//    val courseResponse = CourseResponse(id=1L, title="abc",description=null, status="CLOSED", maxApplicants=30, numApplicants=30)
//    val courseResponse2 = CourseResponse(id=1L, title="abc",description=null, status="CLOSED", maxApplicants=30, numApplicants=30)
//    println(courseResponse.toString())
//    println(courseResponse.equals(courseResponse2))
//}