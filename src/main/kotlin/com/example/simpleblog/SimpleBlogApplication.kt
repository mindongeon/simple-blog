package com.example.simpleblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleBlogApplication

fun main(args: Array<String>) {
    runApplication<SimpleBlogApplication>(*args)
}

/**
 * dev : aws ec2(프리티어) + s3 + codeeploy + github action
 *
 * back : springboot + kotlin + JPA
 *
 * front : react + typescript + zustand
 */