package com.example.courseregistration.domain.course.service

import com.example.courseregistration.domain.course.dto.CourseResponse
import com.example.courseregistration.domain.course.dto.CreateCourseRequest
import com.example.courseregistration.domain.course.dto.UpdateCourseRequest
import com.example.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.example.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.example.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.example.courseregistration.domain.lecture.dto.AddLectureRequest
import com.example.courseregistration.domain.lecture.dto.LectureResponse
import com.example.courseregistration.domain.lecture.dto.UpdateLectureRequest

interface CourseService {
    // Controller의 각 메소드가 실제로 호출할 함수들. 매개변수도 그대로 등록해준다.
    fun getAllCourseList(): List<CourseResponse>  // 컨트롤러가 이 응답을 받아 상태코드와 함께 응답을 준다.
    fun getCourseById(courseId :Long):CourseResponse
    fun createCourse(request:CreateCourseRequest):CourseResponse
    fun updateCourse(courseId:Long, request:UpdateCourseRequest):CourseResponse
    fun deleteCourse(courseId: Long)

    fun getLectureList(courseId: Long) : List<LectureResponse>

    fun getLecture(courseId: Long, lectureId:Long):LectureResponse

    fun addLecture(courseId:Long, request : AddLectureRequest) : LectureResponse

    fun updateLecture(courseId:Long, lectureId:Long, request: UpdateLectureRequest) : LectureResponse

    fun removeLecture(courseId:Long, lectureId: Long)

    fun applyCourse(courseId: Long, request: ApplyCourseRequest):CourseApplicationResponse

    fun getCourseApplication(courseId: Long, applicationId:Long):CourseApplicationResponse

    fun getCourseApplicationList(courseId: Long):List<CourseApplicationResponse>

    fun updateCourseApplicationStatus(courseId: Long, applicationId: Long, request: UpdateApplicationStatusRequest) :CourseApplicationResponse
    fun searchCourseList(title: String): List<CourseResponse>


}