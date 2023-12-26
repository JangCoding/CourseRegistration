package com.example.courseregistration.domain.course.repository

import com.example.courseregistration.domain.course.model.Course
import org.springframework.data.jpa.repository.JpaRepository

// Jpa에 자동으로 연결되기 때문에 어노테이션 필요 없음

                                  // T
interface CourseRepository : JpaRepository<Course, Long> {

}