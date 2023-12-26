package com.example.courseregistration.domain.user.model

import com.example.courseregistration.domain.courseapplication.model.CourseApplication
import com.example.courseregistration.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
        @Column(name = "email")
        val email:String,

        @Column(name = "password")
        val password : String,

        @Embedded // Embeddable 한 객체를 Embed 한다. 가져온다.
        var profile : Profile,

        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        val role : UserRole,

        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
        var courseApplications:MutableList<CourseApplication> = mutableListOf()
    ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        nickname = profile.nickname,
        email = email,
        role = role.name
    )
}
