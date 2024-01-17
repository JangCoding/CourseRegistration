package com.example.courseregistration.infra.security.jwt

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordEncoderConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder{ // 필요한 Service 에 주입하면 됨
        return BCryptPasswordEncoder() // 암호와 인코딩 기술
    }
}