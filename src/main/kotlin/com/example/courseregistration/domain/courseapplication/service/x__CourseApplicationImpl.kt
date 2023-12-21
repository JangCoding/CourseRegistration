package com.example.courseregistration.domain.courseapplication.service

import com.example.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.example.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.example.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import org.springframework.stereotype.Service

@Service
class x__CourseApplicationImpl():x__CourseApplicationService {
    override fun getAllApplicationList(courseId: Long): List<CourseApplicationResponse> {
        TODO("Not yet implemented")
    }

    override fun getApplitionById(courseId: Long, applicationId: Long): CourseApplicationResponse {
        TODO("Not yet implemented")
    }

    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
        TODO("Not yet implemented")
    }

    override fun updateApplicationStatus(
        courseID: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {
        TODO("Not yet implemented")
    }
}