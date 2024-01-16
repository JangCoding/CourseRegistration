package com.example.courseregistration.infra.aop

import org.aopalliance.intercept.Joinpoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

//@Aspect  // 사용 안할 땐 빈 떼버리면 됨
//@Component // bean 으로 등록
class TestAop {

    // JoinPoint 를 기준으로 정확히 어느 위치에 적용 되는지
            // pointcut Expression, Pointcut Designator : execution, annotaion ...       ///.*(..) 모든 메서드 // .. : 인자가 1개 이상
    @Around("execution(* com.example.courseregistration.domain.course.service.CourseService.getCourseById(..))") // joinpoint가 실행되는 곳 의 앞뒤로 명령어 설정 . pointcut 설정
    fun thisIsAdvice(joinpoint: ProceedingJoinPoint) { // argumnet 는 항상 joinpoint 를 받는다
        println("AOP START")
        joinpoint.proceed()
        println("AOP END")

    }

}