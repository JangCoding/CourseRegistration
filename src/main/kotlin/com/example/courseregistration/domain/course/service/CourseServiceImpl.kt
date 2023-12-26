package com.example.courseregistration.domain.course.service

import com.example.courseregistration.domain.course.dto.CourseResponse
import com.example.courseregistration.domain.course.dto.CreateCourseRequest
import com.example.courseregistration.domain.course.dto.UpdateCourseRequest
import com.example.courseregistration.domain.course.model.Course
import com.example.courseregistration.domain.course.model.CourseStatus
import com.example.courseregistration.domain.course.model.toResponse
import com.example.courseregistration.domain.course.repository.CourseRepository
import com.example.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.example.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.example.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.example.courseregistration.domain.courseapplication.model.CourseApplication
import com.example.courseregistration.domain.courseapplication.model.CourseApplicationStatus
import com.example.courseregistration.domain.courseapplication.model.toResponse
import com.example.courseregistration.domain.courseapplication.repository.CourseApplicationRepository
import com.example.courseregistration.domain.exception.ModelNotFoundException
import com.example.courseregistration.domain.lecture.dto.AddLectureRequest
import com.example.courseregistration.domain.lecture.dto.LectureResponse
import com.example.courseregistration.domain.lecture.dto.UpdateLectureRequest
import com.example.courseregistration.domain.lecture.model.Lecture
import com.example.courseregistration.domain.lecture.model.toResponse
import com.example.courseregistration.domain.lecture.repository.LectureRepository
import com.example.courseregistration.domain.user.repository.UserRepository
import com.example.courseregistration.domain.user.model.User
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service // @Component 해도 되지만 명시하기 위해.
//impliment(구현)의 약자
class CourseServiceImpl(
    // Repository 추가 후 연결
    private val courseRepository: CourseRepository,
    private val lectureRepository: LectureRepository,
    private val courseApplicationRepository: CourseApplicationRepository,
    private val userRepository: UserRepository,
) : CourseService {
    // Ctrl + O 하면 CourseService 안의 기능을 오버라이드할 수 있도록 도와줌
    override fun getAllCourseList(): List<CourseResponse> {
        // TODO : 할일을 미리 적어두면 좋음. 미개발.
        // TODO : DB에서 모든 Course 목록을 조회하여 CourseResponse 목록으로 변환 후 반환

        // toResponse() 최하단에 정의 Course의 값을 Repository 형태로 변환하여 리턴.
        return courseRepository.findAll().map { it.toResponse() }
    }

    override fun getCourseById(courseId: Long): CourseResponse {
        // TODO : DB 에서 ID 기반으로 Course 가져와서 CourseResponse 로 변환 후 반환
        // TODO : 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException 공통으로 사용할 수 있는 네이밍
        //throw ModelNotFoundException(modelName = "Course", id=1L)

        var course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.toResponse()

    }

    @Transactional
    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        // TODO : request 를 Course라는 Entity 로 변환 후 DB에 저장(.save() Jpa 메서드 )
        return courseRepository.save(
            Course(
                title = request.title,
                description = request.description,
                status = CourseStatus.OPEN,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        // TODO : DB(courseRepository)에서 courseId에 해당하는 Course를 가져와서 request기반으로 업데이트 후 DB에 저장, 결과를 CourseResponse로 변환 후 반환
        // TODO : 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        course.title = request.title
        course.description = request.description

//        val (title, description) = request
//        course.title = title
//        course.description = description

        return courseRepository.save(course).toResponse()    }

    @Transactional
    override fun deleteCourse(courseId: Long) {
        // TODO : DB에서 courseId에 해당하는 Course를 삭제, 연관된 CourseApplication과 Lecture도 모두 삭제한다.
        // TODO : 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        courseRepository.delete(course)
    }

    @Transactional
    override fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course를 가져와서 Lecture를 추가 후 DB에 저장, 결과를을 LectureResponse로 변환 후 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        val lecture = Lecture(
            title = request.title,
            videoUrl = request.videoUrl,
            course = course
        )

        // Course에 Lecture 추가
        course.addLecture(lecture)
        // Lecture에 영속성 전파
        courseRepository.save(course)
        return lecture.toResponse()
    }

    override fun getLecture(courseId: Long, lectureId: Long): LectureResponse {
        // TODO: 만약 courseId, lectureId에 해당하는 Lecture가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져와서 LectureResponse로 변환 후 반환

        //val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        //course.lecture.find{ it.id == lectureId } // course 내부의 모든 lecture 를 가져와야 하기 때문에 비효율적.

        // lectureId 까지 매개변수로 입력받아 한번에 원하는 lecture를 찾기
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId)
            ?: throw ModelNotFoundException("Lecture", lectureId)

        return lecture.toResponse()

    }

    override fun getLectureList(courseId: Long): List<LectureResponse> {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course목록을 가져오고, 하위 lecture들을 가져온 다음, LectureResopnse로 변환해서 반환
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        return course.lectures.map{it.toResponse()}
    }

    @Transactional
    override fun updateLecture(courseId: Long, lectureId: Long, request: UpdateLectureRequest): LectureResponse {
        // TODO: 만약 courseId, lectureId에 해당하는 Lecture가 없다면 throw ModelNotFoundException
        /* TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져와서
            request로 업데이트 후 DB에 저장, 결과를을 LectureResponse로 변환 후 반환 */

        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId)
            ?: throw ModelNotFoundException("Course", courseId)

        lecture.title = request.title
        lecture.videoUrl = request.videoURL

        return lectureRepository.save(lecture).toResponse()


    }

    @Transactional
    override fun removeLecture(courseId: Long, lectureId: Long) {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져오고, 삭제
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val lecture = lectureRepository.findByCourseIdAndId(courseId, lectureId)
            ?: throw ModelNotFoundException("Course", courseId)

        // lecture 의 생성과 삭제를 course가 관리하도록
        course.removeLecture(lecture)

        //lectureRepository.delete(lecture) // 로 해도 가능

        courseRepository.save(course)
    }

    @Transactional
    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: 만약 course가 이미 마감됐다면, throw IllegalStateException
        // TODO: 이미 신청했다면, throw IllegalStateException
        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val user = userRepository.findByIdOrNull(request.userId)
            ?: throw ModelNotFoundException("User", request.userId)

        // Course 마감여부 확인 위해 새로 생성한 함수
        if (course.isClosed()){
            throw IllegalStateException("Course is already closed. courseId : $courseId")
        }

        // CourseApplication 중복 체크
        if (courseApplicationRepository.existsByCourseIdAndUserId(courseId, request.userId)){
            throw IllegalStateException("Already applied. courseId : $courseId, userId : ${request.userId}")
        }

        val courseapplication = CourseApplication(
            // status 없는 이유 : PENDING 설정 해놓아서
            course = course,
            user = user
        )

        courseRepository.save(course)

        return courseapplication.toResponse()
    }

    override fun getCourseApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        // TODO: 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, applicationId에 해당하는 CourseApplication을 가져와서 CourseApplicationResponse로 변환 후 반환

        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        return application.toResponse()
    }

    override fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse> {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course를 가져오고, 하위 courseApplication들을 CourseApplicationResponse로 변환 후 반환

        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)

        return course.courseApplications.map { it.toResponse() }
    }

    @Transactional
    override fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {
        TODO("Not yet implemented")// TODO: 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // TODO: 만약 status가 이미 변경된 상태면 throw IllegalStateException
        // TODO: Course의 status가 CLOSED상태 일시 throw IllegalStateException
        // TODO: 승인을 하는 케이스일 경우, course의 numApplicants와 maxApplicants가 동일하면, course의 상태를 CLOSED로 변경
        // TODO: DB에서 courseApplication을 가져오고, status를 request로 업데이트 후 DB에 저장, 결과를 CourseApplicationResponse로 변환 후 반환

        val course = courseRepository.findByIdOrNull(courseId) ?: throw ModelNotFoundException("Course", courseId)
        val application = courseApplicationRepository.findByCourseIdAndId(courseId, applicationId)
            ?: throw ModelNotFoundException("CourseApplication", applicationId)

        // 이미 승인 혹은 거절 신청건인지 체크
        if (application.isProceeded()){ // 0 : Pending , 1 Accepted, 2 Rejected
            throw IllegalStateException("Application is already proceeded. applicationId: $applicationId")
        }

        // Course 마감 여부 체크
        if (course.isClosed()){
            throw IllegalStateException("Course is already closed. courseId: $courseId")
        }

        when (request.status){
            //승인 일때
            CourseApplicationStatus.ACCEPTED.name -> {
                //승인 처리
                application.accept()
                //Course의 신청 인원 늘려줌
                course.addApplicant()
                //만약 인원이 꽉 차면 마감처리
                if(course.isFull()){
                    course.close()
                }
                courseRepository.save(course)
            }

            //거절일 때
            CourseApplicationStatus.REJECTED.name -> {
                //거절 처리
                application.reject()
            }

            // 그 외 다른 인자
            else ->{
                throw IllegalStateException("Invalid status : ${request.status}")
            }
        }
    }
}