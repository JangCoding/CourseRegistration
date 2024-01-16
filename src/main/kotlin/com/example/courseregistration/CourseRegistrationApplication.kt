package com.example.courseregistration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy


@EnableAspectJAutoProxy // 프록시 기반으로 aop 사용 가능. SpringAOP가 AspectJ 기반으로 개발되어서
@SpringBootApplication
class CourseRegistrationApplication

fun main(args: Array<String> ) {
	runApplication<CourseRegistrationApplication>(*args)
}
