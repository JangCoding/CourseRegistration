package com.example.courseregistration.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable


@Embeddable // 다른 Entity 안에 종속되어 갖다 쓸 수 있다

data class Profile (
        @Column(name = "nickname")
        var nickname : String,
){
}