package com.example.simpleblog.domain.member

import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

/**
 * memberRepository는 @Repository로 bean을 띄우지 않아도 된다.
 * JpaRepository를 상속받고 있기 때문에
 */
interface MemberRepository : JpaRepository<Member, Long>, MemberCustomRepository {

}

interface MemberCustomRepository {
    fun findMembers(pageable: Pageable): Page<Member>
}

class MemberCustomRepositoryImpl(
    private val queryFactory: SpringDataQueryFactory
) : MemberCustomRepository {

    val log = KotlinLogging.logger { }
    override fun findMembers(pageable: Pageable): Page<Member> {
        val result = queryFactory.listQuery<Member> {
            select(entity(Member::class))
            from(entity(Member::class))
            limit(pageable.pageSize)
            offset(pageable.offset.toInt())
            orderBy(column(Member::id).desc())
        }

        val countQuery = queryFactory.listQuery<Member> {
            select(entity(Member::class))
            from(entity(Member::class))
        }
        return PageableExecutionUtils.getPage(result, pageable) {
            countQuery.size.toLong()
        }
    }
}