package com.example.courseregistration.domain.lecture.controller

import com.example.courseregistration.domain.lecture.dto.AddLectureRequest
import com.example.courseregistration.domain.lecture.dto.LectureResponse
import com.example.courseregistration.domain.lecture.dto.UpdateLectureRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/courses/{courseId}/lectures")
@RestController
class LectureController {
    @GetMapping()
    fun getLectureList(
        @PathVariable courseId:String
    ):ResponseEntity<List<LectureResponse>> {
        TODO()
    }
    @GetMapping("/{lectureId}")
    fun getLecture(
        @PathVariable courseId:String,
        @PathVariable lectureId: Long
    ):ResponseEntity<LectureResponse>{
        TODO()
    }
    @PostMapping()
    fun addLecture(
        @PathVariable courseId:String,
        @RequestBody addLectureRequest: AddLectureRequest
    ):ResponseEntity<LectureResponse>{
        TODO()
    }
    @PutMapping("/{lectureId}")
    fun updateLecture(
        @PathVariable courseId:String,
        @RequestBody updateLectureRequest: UpdateLectureRequest,
        @PathVariable lectureId:Long
    ):ResponseEntity<LectureResponse>{
        TODO()
    }
    @DeleteMapping("/{lectureId}")
    fun removeLecture(
        @PathVariable courseId:String,
        @PathVariable lectureId:Long
        // Unit = Void 비슷한 뜻
    ):ResponseEntity<Unit>{
        TODO()
    }


}