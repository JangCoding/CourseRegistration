package com.example.courseregistration.infra.aop

@Target(AnnotationTarget.FUNCTION) // 어노테이션이 적용될 대상.
@Retention(AnnotationRetention.RUNTIME) // 어느 시점 까지 사용될 수 있는 지 여부 . 디폴트:런타임.
annotation class MyStopWatch()
