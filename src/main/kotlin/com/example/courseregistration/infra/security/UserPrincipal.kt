package com.example.courseregistration.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val email:String,
    val authoricies : Collection<GrantedAuthority>
    // role : 단순 string 아닌 GrantedAuthority(권한이름들 담겨있음) 적용.
){

    // 인스턴스 생성시 초기화 블록
    constructor(id:Long,email:String,roles:Set<String>):this(
        id,
        email,
        roles.map{SimpleGrantedAuthority("ROLE_$it")}
    )
}
