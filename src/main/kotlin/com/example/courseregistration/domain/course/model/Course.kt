package com.example.courseregistration.domain.course.model

import com.example.courseregistration.domain.course.dto.CourseResponse
import com.example.courseregistration.domain.courseapplication.model.CourseApplication
import com.example.courseregistration.domain.lecture.model.Lecture
import jakarta.persistence.*


@Entity
@Table(name = "course")
class Course(
    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Enumerated(EnumType.STRING) // enum 에 있는 String 으로 DB에 저장해라. 숫자로 하면 나중에 변경 시 섞일 수 있어서
    @Column(name = "status")
    var status: CourseStatus, // 실수 방지 ENUM 클래스로

    @Column(name = "max_applicants")
    val maxApplicants: Int = 30,

    @Column(name = "num_applicants")
    var numApplicants: Int = 0,


    // 양방향 관계일 때 연관관계 주입. 주인이 아닌 쪽에? FK 없는 쪽 , 지연 로딩 설정. 성능향상.
    // casecade : 영속성 전파
    // orphanRemoval : 연결이 끊어져서 고아가 되는 Entity도 삭제해버리기
    //mappedBy = "course", // 양방향 관계에서 누가 주인인지 확인하기 위해 작성
//    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    @JoinColumn(name = "course_id") // 외래키가 어떤 Column 인지? 누구랑 Join 해야 하는지?
//    var lectures: MutableList<Lecture> = mutableListOf(),

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var courseApplications: MutableList<CourseApplication> = mutableListOf()

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun isFull(): Boolean {
        return numApplicants >= maxApplicants
    }

    fun isClosed(): Boolean {
        return status == CourseStatus.CLOSED
    }

    fun close() {
        status = CourseStatus.CLOSED
    }

    fun addApplicant() {
        numApplicants += 1
    }

//    fun addLecture(lecture: Lecture) {
//        lectures.add(lecture)
//    }
//
//    fun removeLecture(lecture: Lecture) {
//        lectures.remove(lecture)
//    }

    fun addCourseApplication(courseApplication: CourseApplication) {
        courseApplications.add(courseApplication)
    }

}

// Course의 값을 CourseResponse 형태로 변환하여 리턴.
fun Course.toResponse(): CourseResponse {
    return CourseResponse(
        id = id!!,
        title = title,
        description = description,
        status = status.name,
        maxApplicants = maxApplicants,
        numApplicants = numApplicants
    )
}