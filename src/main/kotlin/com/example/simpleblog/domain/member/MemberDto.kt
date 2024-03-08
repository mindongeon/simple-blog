package com.example.simpleblog.domain.member

/**
 * dto <=> entity 간의 맵핑할 때, 스타일 2개
 *
 * 1. 각 dto, entity에 책임 할당하는 스타일
 * 2. entitymapper라는 인터페이스나 클래스를 하나 만들어 담당하게 하는 스타일
 */

/**
 * getter, setter 자동으로 만들어줌 data class
 */
data class MemberSaveReq(
    val email: String,
    val password: String,
    val role: Role
)


/**
 * class.function() 식으로 사용하면 실제 data class는 순수하게 Dto로 매핑되는 필드들만 정의가 된다.
 */
fun MemberSaveReq.toEntity(): Member {
    return Member(
        email = this.email,
        password = this.password,
        role = this.role
    )
}

data class MemberRes(
    val id:Long,
    val email:String,
    val password: String,
    val role:Role
)
