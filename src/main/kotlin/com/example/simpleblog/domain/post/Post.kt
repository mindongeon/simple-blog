package com.example.simpleblog.domain.post

import jakarta.persistence.*

@Entity
@Table(name = "Post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "title", nullable = false)
    var title:String,

    @Column(name = "content")
    var content:String
) {

}