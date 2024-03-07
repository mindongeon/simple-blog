package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.AuditingEntity
import jakarta.persistence.*

@Entity
@Table(name = "Post")
class Post(
    title: String,
    content: String
) : AuditingEntity() {
    /**
     * 빌더 패턴 사용하지 않고 생성자를 사용해 주입받음
     */
    @Column(name = "title", nullable = false)
    var title: String = title
        private set

    @Column(name = "content")
    var content: String = content
        private set
}