package com.example.courseregistration.domain.courseapplication.service

import com.example.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.example.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.example.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest

//인터페이스로 생성
interface x__CourseApplicationService{ //: CourseService {
    // Controller의 각 메소드가 실제로 호출할 함수들. 매개변수도 그대로 등록해준다.
    fun getAllApplicationList(courseId:Long):List<CourseApplicationResponse>
    fun getApplitionById(courseId:Long, applicationId:Long):CourseApplicationResponse
    fun applyCourse(courseId:Long, request:ApplyCourseRequest):CourseApplicationResponse
    fun updateApplicationStatus(courseID:Long, applicationId:Long, request:UpdateApplicationStatusRequest):CourseApplicationResponse

}