package com.example.courseregistration.domain.user.repository

import com.example.courseregistration.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    // 메서드 이름으로 Query 작성하기
    fun existsByEmail(email: String): Boolean

    // Query문을 메서드와 연결하기
    @Query("select u from User u where u.email =:email")
    fun findByEmail(email:String):User?

}