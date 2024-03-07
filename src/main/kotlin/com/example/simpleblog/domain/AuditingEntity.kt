package com.example.simpleblog.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 코틀린은 함수가 일급 객체
 */
@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntity(
) : AuditingEntityId() {
    /**
     * 늦은 초기화
     * 업데이트 타임을 기록하기 위해
     *
     * JPA 설계를 하다보면 setter에 대한 것을 막는데
     */
    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    lateinit var createAt: LocalDateTime
        private set

    @LastModifiedDate
    @Column(name = "update_at")
    lateinit var updateAt: LocalDateTime
        private set
}

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntityId : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        //      setter 설정
        private set
}
