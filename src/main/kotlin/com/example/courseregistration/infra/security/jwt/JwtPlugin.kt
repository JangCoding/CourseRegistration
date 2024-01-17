package com.example.courseregistration.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.Date


@Component
class JwtPlugin(
    @Value("\${auth.jwt.issuer}") private val issuer : String,
    @Value("\${auth.jwt.secret}") private val secret : String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour : Long,
) {

    // 매번 교체하고 빌드하고 재배포해야 하므로 application.yml 로 이동
//    companion object {
//        const val SECRET = "gH7kRw9FpD3yN2mQxL5aJcUvZ1oP4bS6" // 아무키 32자 테스트
//        const val ISSUER = "team.sparta.com"
//        const val ACCESS_TOKEN_EXPIRATION_HOUR : Long = 168
//    }


    // 검증 메서드
    fun validateToken(jwt : String) : Result<Jws<Claims>> { // Exception 처리할 때 Result 객체를 리턴하고, 받는 쪽에서 예외처리하도록 <*> 뭐든 올수있음
        return kotlin.runCatching { //try{}catch{} 대신 사용. isFailure, onSuccess 등 사용 가능
            val key = Keys.hmacShaKeyFor( secret.toByteArray(StandardCharsets.UTF_8) )

            // return 따로 명시 없어도 마지막 줄이 runCatching 에 의해 전달됨
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt) // 검증해주기.

        }
    }

    // 액세스 토큰 생성
    fun generateAccessToken(subject:String, email : String, role : String) : String{ // 토큰은 단순 String
        return generateToken(subject, email, role, Duration.ofHours(accessTokenExpirationHour))
    }

    // fun refreshAccessToken() ~~

                                   //expirationPeriod 만 추가로 입력받도록 해서 refresh 시에도 사용할 수 있도록
        private fun generateToken(subject:String, email : String, role : String, expirationPeriod: Duration) : String{
        val claims : Claims = Jwts.claims() // 커스텀 클레임 생성
            .add(mapOf("role" to role, "email" to email))
            .build()

        //val expirationPeriod = Duration.ofHours(168)
        val now = Instant.now()
        val key = Keys.hmacShaKeyFor( secret.toByteArray(StandardCharsets.UTF_8) )


        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod))) // 만료기간
            .claims(claims)
            .signWith(key)
            .compact()
    }
}