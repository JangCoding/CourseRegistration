package com.example.courseregistration.domain.user.controller

import com.example.courseregistration.domain.user.dto.LoginUserRequest
import com.example.courseregistration.domain.user.dto.SignUpRequest
import com.example.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.example.courseregistration.domain.user.dto.UserResponse
import com.example.courseregistration.domain.user.service.UserService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

// @RequestMapping() 굳이 안써도 됨
@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest):ResponseEntity<UserResponse>{
        TODO()
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.signUp(signUpRequest))
    }

    @PutMapping("/users/{userId}/profile")
    fun updateUser(
        @RequestBody updateUserProfileRequest: UpdateUserProfileRequest,
        @PathVariable userId:Long
    ):ResponseEntity<UserResponse>{
        TODO()
        return ResponseEntity.status(HttpStatus.OK)
            .body(userService.updateUserProfile(userId, updateUserProfileRequest))
    }

    //@Exception 은 Global 에서

//    @PostMapping("/login")
//    fun loginUser(@RequestBody loginUserRequest: LoginUserRequest):ResponseEntity<UserResponse>{
//        TODO()
//    }
//    @PostMapping("/logout")
//    fun logoutUser(){
//        TODO()
//    }
}