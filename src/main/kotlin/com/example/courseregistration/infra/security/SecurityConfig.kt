package com.example.courseregistration.infra.security

import com.example.courseregistration.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity // http 기반 통신시 관련 보안 기능 켜기위해 설정
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint
){ // 필요 없는 필터 끄기 위해 작성.
    @Bean
    fun filterChain(http: HttpSecurity):SecurityFilterChain{
        return http
            .httpBasic{it.disable()}  // 사용하지 않는 필터 끄기
            .formLogin{it.disable()}
            .csrf{ it.disable()}
            //인증되야 넘어갈 수 있도록
            .authorizeHttpRequests{
                it.requestMatchers(
                    "/login",
                    "/signup",
                    "/swagger-ui/**", // swagger페이지
                    "v3/api-docs/**", // 내용 docs
                ).permitAll() // 위 URL은 승인처리
                    // 위 URI 제외하곤 모두(anyRequest) 인증이 되어야 함
                    .anyRequest().authenticated()
            }
            // 기존 UsernamePasswordAuthenticationFilter 가 존재하던 자리에 JwtAuthenticationFilter 추가
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling{
                it.authenticationEntryPoint(authenticationEntryPoint)
            }
            .build()
    }
}