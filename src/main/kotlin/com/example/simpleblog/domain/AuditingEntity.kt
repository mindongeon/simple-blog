package com.example.simpleblog.domain

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 코틀린은 함수가 일급 객체
 */
@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntity (
) {
    /**
     * 늦은 초기화
     * 업데이트 타임을 기록하기 위해
     *
     * JPA 설계를 하다보면 setter에 대한 것을 막는데
     *
     */
    lateinit var createAt: LocalDateTime
    lateinit var updateAt: LocalDateTime
}

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntityId: Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
