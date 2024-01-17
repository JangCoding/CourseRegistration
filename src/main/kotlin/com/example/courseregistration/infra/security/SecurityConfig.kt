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
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
){ // 필요 없는 필터 끄기 위해 작성.
    @Bean
    fun filterChain(http: HttpSecurity):SecurityFilterChain{
        return http
            .httpBasic{it.disable()}  // 사용하지 않는 필터 끄기
            .formLogin{it.disable()}
            .csrf{ it.disable()}
            .authorizeHttpRequests{
                it.requestMatchers(
                    "/login",
                    "/signup",
                    "/swagger-ui/**",
                    "v3/api-docs/**",
                ).permitAll() // 위 URI 제외하곤 모두 인증이 되어야 함
                    .anyRequest().authenticated()
            }
            // 기존 UsernamePasswordAuthenticationFilter 가 존재하던 자리에 JwtAuthenticationFilter 적용
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}