package com.example.courseregistration.domain.user.controller

import com.example.courseregistration.domain.user.dto.LoginUserRequest
import com.example.courseregistration.domain.user.dto.SignUpRequest
import com.example.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.example.courseregistration.domain.user.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

// @RequestMapping() 굳이 안써도 됨
@RestController
class UserController {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest):ResponseEntity<UserResponse>{
        TODO()
    }
    @PostMapping("/login")
    fun loginUser(@RequestBody loginUserRequest: LoginUserRequest):ResponseEntity<UserResponse>{
        TODO()
    }
    @PutMapping("/users/{userId}/profile")
    fun updateUser(
        @RequestBody updateUserProfileRequest: UpdateUserProfileRequest,
        @PathVariable userId:String
    ):ResponseEntity<UserResponse>{
        TODO()
    }
    @PostMapping("/logout")
    fun logoutUser(){
        TODO()
    }
}