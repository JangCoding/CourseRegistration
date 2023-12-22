package com.example.courseregistration.domain.lecture.Model

import com.example.courseregistration.domain.course.Model.Course
import com.example.courseregistration.domain.course.model.Course
import jakarta.persistence.*
import org.hibernate.annotations.Fetch

@Entity
@Table(name = "lecture")
class Lecture(
    @Column(name = "title")
    var title: String,

    @Column(name = "video_url")
    var videoUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)  // 1:N 관계에서 FK(course_id) 를 들고 있기 때문에 연관관계의 주인이 됨? // 주인 아닌 쪽에 mappedBy
    @JoinColumn(name = "course_id") // MappedBy 할 때 알아서 추적하지만 명시적으로 표현
    val course: Course

    ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null
}