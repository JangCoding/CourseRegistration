package com.example.courseregistration.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Component
@Aspect

class StopWatchAspect {

    private val logger = LoggerFactory.getLogger("Execution Time Logger")


    // 이후 @StopWatch 달아주면 적용됨
    @Around("@annotation(com.example.courseregistration.infra.aop.MyStopWatch)")
    fun run(joinPoint: ProceedingJoinPoint){
        val stopWatch = StopWatch()
        stopWatch.start()
        joinPoint.proceed()
        stopWatch.stop()

        val methodName = joinPoint.signature.name
        val methodArgumments = joinPoint.args

        val timeElapsedMs = stopWatch.totalTimeMillis // stop-start 사이 ms 측정
        logger.info("Method Name : ${methodName} | Arguments : ${methodArgumments.joinToString(", ") } | Execution Time : ${timeElapsedMs}")
    }
}