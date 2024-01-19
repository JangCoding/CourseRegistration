package com.example.courseregistration.infra.security.jwt

import com.example.courseregistration.infra.security.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails

class JwtAuthenticationToken(
    // Authentication 객체 내부에 담겨있음. User의 식별자. Id, Email 등
    // [ Principal , Credentials(비밀번호 등) , Authorities(권한 정보, role, Scope)
    private val principal : UserPrincipal,

    // 요청하고 있는 주소, 세션 정보
    details : WebAuthenticationDetails

) : AbstractAuthenticationToken(principal.authoricies) { // .authoricies = role
    // 사용자 인증 정보 표현하기 위한 추상 클래스.
    //

    init {
        // JWT 검증 됐을 때 바로 생성. 생성 되면 true 로
        super.setAuthenticated(true)
        super.setDetails(details)
    }
    override fun getPrincipal() = principal
    override fun getCredentials() = null // 비밀번호 남기지 않아서 null로

    //인증 여부 조회
    override fun isAuthenticated(): Boolean {
        return true // 이미 위에서 setAuthenticated(true) 처리하고 있긴 함
    }
}