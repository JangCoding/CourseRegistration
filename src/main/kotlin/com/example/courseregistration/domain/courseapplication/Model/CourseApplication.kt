package com.example.courseregistration.domain.courseapplication.Model

import com.example.courseregistration.domain.course.Model.Course
import com.example.courseregistration.domain.user.Model.User
import com.example.courseregistration.domain.user.Model.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "course_application")
class CourseApplication(
        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        var status : CourseApplicationStatus,

        @ManyToOne
        @JoinColumn(name = "course_id") // FK
        val course : Course,

        @ManyToOne
        @JoinColumn(name = "user_id") // FK
        val user : User

        ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null
}