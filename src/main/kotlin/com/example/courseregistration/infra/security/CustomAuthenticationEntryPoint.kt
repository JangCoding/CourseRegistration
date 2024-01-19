package com.example.courseregistration.infra.security

import com.example.courseregistration.domain.exception.dto.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
//커스텀 인증 진입 지점. 인증 예외가 발생했을 때 클라이언트에게 적절한 응답을 제공
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest, // 자바 기반이라 ? 빼야됨
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {

        response.status = HttpServletResponse.SC_UNAUTHORIZED // 에러 상태 메세지 선택
        response.contentType = MediaType.APPLICATION_JSON_VALUE // return 형태 알려주기
        response.characterEncoding = "UTF-8" //  charcter 형태 알려주기

        val objectMapper = ObjectMapper() // 잭슨이라는 객체를 맵핑하는 라이브러리의 클래스?
        val jsonString = objectMapper.writeValueAsString(ErrorResponse("JWT Verification failed"))
        response.writer.write(jsonString) // Response에 JSON String을 그냥 써주는것
    }
}