package com.example.courseregistration.domain.courseapplication.controller

import com.example.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.example.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.example.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.example.courseregistration.domain.courseapplication.service.CourseApplicationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/courses/{courseId}/applications")
@RestController
//Service 를 사용하려면 생성자를 주입받아야함.
class CourseApplicationController(
    val courseApplicationService : CourseApplicationService
){
    @GetMapping()
    fun getApplicationList(
        @PathVariable courseId:Long
    ) : ResponseEntity<List<CourseApplicationResponse>>{
        TODO()
        return ResponseEntity.status(HttpStatus.OK).body(courseApplicationService.getAllApplicationList(courseId))
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId:Long,
        @PathVariable applicationId:Long
    ) : ResponseEntity<CourseApplicationResponse>{
        TODO()
        return ResponseEntity.status(HttpStatus.OK).body(courseApplicationService.getApplitionById(courseId, applicationId))
    }

    @PostMapping()
    fun applyCourse(
        @PathVariable courseId:Long,
        @RequestBody applyCourseRequest : ApplyCourseRequest,
    ) : ResponseEntity<CourseApplicationResponse>{
        TODO()
        return ResponseEntity.status(HttpStatus.OK).body(courseApplicationService.applyCourse(courseId, applyCourseRequest))
     }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId:Long,
        @PathVariable applicationId:Long,
        @RequestBody updateApplicationStatusRequest : UpdateApplicationStatusRequest
    ) : ResponseEntity<CourseApplicationResponse>{
        TODO()
        return ResponseEntity.status(HttpStatus.OK).body(courseApplicationService.updateApplicationStatus(courseId, applicationId, updateApplicationStatusRequest))

    }
}

