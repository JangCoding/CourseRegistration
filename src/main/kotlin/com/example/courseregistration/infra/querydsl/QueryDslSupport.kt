package com.example.courseregistration.infra.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

// 직접 객체를 만들어서 사용할 것은 아니라 추상클래스로 생성
abstract class QueryDslSupport {

    @PersistenceContext
    protected lateinit var entitymanager: EntityManager

    // 다른 곳에서도 쓸 수 있게 생성
    protected val queryFactory : JPAQueryFactory
        //geter 설정.
        get(){
            // return 시 entityManager 필요
            return JPAQueryFactory(entitymanager)
        }
}