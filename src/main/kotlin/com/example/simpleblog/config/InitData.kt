package com.example.simpleblog.config

import com.example.simpleblog.domain.member.*
import com.example.simpleblog.domain.post.*
import io.github.serpro69.kfaker.faker
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class InitData(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository
) {
    val faker = faker {  }
    private val log = KotlinLogging.logger {}

    @EventListener(ApplicationStartedEvent::class)
    private fun init() {

/*
        val member = Member(
            email = faker.internet.safeEmail(),
            password = "1234",
            role = Role.USER
        )
*/
//        val members = generateMembers(100)
//        memberRepository.saveAll(members)
//
//        val posts = generatePosts(100)
//        postRepository.saveAll(posts)

    }

    private fun generatePosts(cnt: Int): MutableList<Post> {
        val posts = mutableListOf<Post>()

        for (i in 1..cnt) {
            val post = generatePost()
            log.info { "insert $post" }
            posts.add(post)
        }

        return posts
    }

    private fun generatePost(): Post = PostSaveReq(
        title = faker.theExpanse.ships(),
        content = faker.quote.matz(),
        memberId = 1
    ).toEntity()

    /**
     * 코틀린에서는 함수가 일급 시민.
     * 마지막에 있는 키워드는 자동으로 return이 추가됨.
     * 또한 자동으로 할당 가능
     */
    private fun generateMember(): Member = MemberSaveReq(
            email = faker.internet.safeEmail(),
            password = "1234",
            role = Role.USER
        ).toEntity()

    private fun generateMembers(cnt: Int): MutableList<Member> {
        val members = mutableListOf<Member>()

        for (i in 1..cnt) {
            val member = generateMember()
            log.info { "insert $member" }
            members.add(member);
        }

        return members
    }
}