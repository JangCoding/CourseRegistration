package com.example.courseregistration.domain.course.repository

import com.example.courseregistration.domain.course.model.Course
import com.example.courseregistration.domain.course.model.QCourse
import com.example.courseregistration.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository // JPARepository 상속받을 경우 자동 등록되서 안해도 되는데 직접 정의할 경우
class QueryDslCourseRepositofy : QueryDslSupport() {

    private val course = QCourse.course

    // 타이틀 기준 검색
    fun searchCourseListByTitle(title:String) : List<Course>{
        return queryFactory.selectFrom(course)
            .where(course.title.containsIgnoreCase(title)) // 대소문자구분 무시
            .fetch()
    }
}