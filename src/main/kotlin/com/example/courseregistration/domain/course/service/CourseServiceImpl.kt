package com.example.courseregistration.domain.course.service

import com.example.courseregistration.domain.course.dto.CourseResponse
import com.example.courseregistration.domain.course.dto.CreateCourseRequest
import com.example.courseregistration.domain.course.dto.UpdateCourseRequest
import com.example.courseregistration.domain.exception.ModelNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service // @Component 해도 되지만 명시하기 위해.
                    //impliment(구현)의 약자
class CourseServiceImpl : CourseService {
    // Ctrl + O 하면 CourseService 안의 기능을 오버라이드할 수 있도록 도와줌
    override fun getAllCourseList(): List<CourseResponse> {
        // TODO : 할일을 미리 적어두면 좋음. 미개발.
        // TODO : DB에서 모든 Course 목록을 조회하여 CourseResponse 목록으로 변환 후 반환

        TODO("Not yet implemented")

    }

    override fun getCourseById(courseId: Long): CourseResponse {
        // TODO : DB 에서 ID 기반으로 Course 가져와서 CourseResponse 로 변환 후 반환
        // TODO : 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException 공통으로 사용할 수 있는 네이밍
        TODO("Not yet implemented")
        throw ModelNotFoundException(modelName = "Course", id=1L)
    }

    @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        // TODO : request 를 Course라는 Entity 로 변환 후 DB에 저장
        TODO("Not yet implemented")
    }
    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        // TODO : DB에서 courseId에 해당하는 Course를 가져와서 request기반으로 업데이트 후 DB에 저장, 결과를 CourseResponse로 변환 후 반환
        // TODO : 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteCourse(courseId: Long) {
        // TODO : DB에서 courseId에 해당하는 Course를 삭제, 연관된 CourseApplication과 Lecture도 모두 삭제한다.
        // TODO : 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        TODO("Not yet implemented")
    }
}