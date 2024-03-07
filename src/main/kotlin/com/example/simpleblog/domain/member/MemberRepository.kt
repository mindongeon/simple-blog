package com.example.simpleblog.domain.member

import org.springframework.data.jpa.repository.JpaRepository

/**
 * memberRepository는 @Repository로 bean을 띄우지 않아도 된다.
 * JpaRepository를 상속받고 있기 때문에
 */
interface MemberRepository: JpaRepository<Member, Long> {

}