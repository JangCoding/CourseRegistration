package com.example.courseregistration.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Component // Bean에 등록
@Aspect // Aspect 를 정의하는 클래스 라는 뜻

class StopWatchAspect {

    private val logger = LoggerFactory.getLogger("Execution Time Logger")


    // 이후 @StopWatch 달아주면 적용됨
    @Around("@annotation(com.example.courseregistration.infra.aop.MyStopWatch)") // 정의할 어노테이션

    fun run(joinPoint: ProceedingJoinPoint){ // ProceedingJoinPoint : aop가 적용되는 메소드.
        val stopWatch = StopWatch() // java.time 의 메서드. 본 메소드명과 관계 없음
        stopWatch.start()
        joinPoint.proceed() // 이 @StopWatch 메소드를 실행하는 메소드의 전 후에 실행 -> Around
        stopWatch.stop()

        val methodName = joinPoint.signature.name // joinPoint 내부 속성으로 확인 가능
        val methodArgumments = joinPoint.args

        val timeElapsedMs = stopWatch.totalTimeMillis // stop-start 사이 ms 측정
        logger.info("Method Name : ${methodName} " +
                "| Arguments : ${methodArgumments.joinToString(", ") } " +
                "| Execution Time : ${timeElapsedMs}")
    }
}