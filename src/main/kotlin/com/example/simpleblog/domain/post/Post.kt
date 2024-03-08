package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.toDto
import jakarta.persistence.*

@Entity
@Table(name = "Post")
class Post(
    title: String,
    content: String,
    member: Member
) : AuditingEntity() {
    /**
     * 빌더 패턴 사용하지 않고 생성자를 사용해 주입받음
     */
    @Column(name = "title", nullable = false)
    var title: String = title
        private set

    @Column(name = "content", length = 1000)
    var content: String = content
        private set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
        private set


    override fun toString(): String {
        return "Post(id=$id title='$title', content='$content', member=$member)"
    }


}

fun Post.toDto(): PostRes = PostRes(
    id = this.id!!,
    title = this.title,
    content = this.content,
    member = this.member.toDto()
)