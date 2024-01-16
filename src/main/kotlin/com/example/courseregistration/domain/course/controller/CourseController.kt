package com.example.courseregistration.domain.course.controller

import com.example.courseregistration.domain.course.dto.CourseResponse
import com.example.courseregistration.domain.course.dto.CreateCourseRequest
import com.example.courseregistration.domain.course.dto.UpdateCourseRequest
import com.example.courseregistration.domain.course.service.CourseService
import com.example.courseregistration.domain.exception.ModelNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//만들었으면 스프링에 Bean 으로 등록해달라고 해야함.
@RestController
// courses 하위로 오는 요청은 여기로 연결을 해줘라 서브렛한테
// 이제 하위 메소드들은 /courses 라는 경로를 생략해도 됨
@RequestMapping("/courses")
                    //CourseService 를 사용하려면 생성자를 주입받아야함.
class CourseController (
    private val courseService: CourseService // 인터페이스를 받아오면 Spring이 알아서 상속받는 Bean을 가져와줌. 만약 Service 없으면 Impl 하나씩 다가져와야됨;
){
    //내부의 각각의 함수가 API 요청 (GET, PUT, ... 등) 과 맵핑이 됨
    @GetMapping()
    fun getCourseList(): ResponseEntity<List<CourseResponse>> {

        // 응답 객체를 보내는데 상태는 OK. 내용은 courseService(인터페이스)의 getAllCourseList() 메서드의 반환값
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourseList())
    }
    @GetMapping("/{courseId}")
    //            {변수} 어노테이션 인자와 네이밍 일치
    fun getCourse(@PathVariable courseId:Long): ResponseEntity<CourseResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(courseId))
    }

    @PostMapping()
    // @RequestBody : createCourseRequest 클라이언트로 요청받은 Json을 객체로 맵핑
    // ResponseEntity<DTO> 컨트롤러에서 클라이언트로 HTTP 응답을 나타내는 클래스.
    // 응답 데이터(DTO)와 함께 HTTP 상태 코드, 헤더 등을 포함. 더 많은 정보를 제공하거나 특정 상태에 대한 응답을 정의.
    fun createCourse(@RequestBody createCourseRequest:CreateCourseRequest): ResponseEntity<CourseResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(createCourseRequest))
    }

    @PutMapping("/{courseId}")
    // PathVariable : URI 에서 경로변수 {} 추출하여 파라미터로 전달받기 위해
    fun updateCourse(@PathVariable courseId:Long, @RequestBody updateCourseRequest: UpdateCourseRequest): ResponseEntity<CourseResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(courseId, updateCourseRequest))
    }

    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable courseId:Long) : ResponseEntity<Unit> {

        courseService.deleteCourse(courseId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
    // Global Exception Handler 로 이동
//    // 해당 클래스에서 예외가 발생하면 핸들링 할 것이다
//    @ExceptionHandler(ModelNotFoundException::class)
//    // e :  예외 코드를 인자로, 반환은 Unit. 없음.
//    fun handleModelNotFoundException(e:ModelNotFoundException) : ResponseEntity<Unit>{
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
//    }

}