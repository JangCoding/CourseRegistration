package com.example.courseregistration.domain.course.service

import com.example.courseregistration.domain.course.dto.CourseResponse
import com.example.courseregistration.domain.course.dto.CreateCourseRequest
import com.example.courseregistration.domain.course.dto.UpdateCourseRequest

interface CourseService {
    // Controller의 각 메소드가 실제로 호출할 함수들. 매개변수도 그대로 등록해준다.
    fun getAllCourseList(): List<CourseResponse>  // 컨트롤러가 이 응답을 받아 상태코드와 함께 응답을 준다.
    fun getCourseById(courseId :Long):CourseResponse
    fun createCourse(request:CreateCourseRequest):CourseResponse
    fun updateCourse(courseId:Long, request:UpdateCourseRequest):CourseResponse
    fun deleteCourse(courseId: Long)
}