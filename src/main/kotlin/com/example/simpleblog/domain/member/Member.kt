package com.example.simpleblog.domain.member

import com.example.simpleblog.domain.AuditingEntity
import jakarta.persistence.*

@Entity
@Table(name = "Member")
class Member(
    email: String,
    password: String,
    role: Role
) : AuditingEntity() {
    @Column(name = "email", nullable = false)
    var email: String = email
        private set

    @Column(name = "password")
    var password: String = password
        private set

    @Enumerated(EnumType.STRING)
    var role: Role = role
        private set

    override fun toString(): String {
        return "Member(email='$email', password='$password', role=$role)"
    }

    companion object {
        fun createFakeMember(memberId: Long): Member {
            val member = Member("","",Role.USER)
            member.id = memberId
            return member
        }
    }
}

fun Member.toDto(): MemberRes = MemberRes(
    id = this.id!!, // !! = non-nullable type
    email = this.email,
    password = this.password,
    role = this.role
)

/**
 * 유저 권한
 */
enum class Role {
    USER, ADMIN
}