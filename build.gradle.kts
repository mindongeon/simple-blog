import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

// 해당 어노테이션에 대하여 open 키워드를 붙이는 효과
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperClass")
}


group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
// https://mvnrepository.com/artifact/io.github.serpro69/kotlin-faker
    implementation("io.github.serpro69:kotlin-faker:1.16.0")
// https://mvnrepository.com/artifact/com.github.gavlyukovskiy/p6spy-spring-boot-starter
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.1")
// https://mvnrepository.com/artifact/io.github.microutils/kotlin-logging
    implementation("io.github.microutils:kotlin-logging:3.0.5")
// https://mvnrepository.com/artifact/com.linecorp.kotlin-jdsl/spring-data-kotlin-jdsl-starter
    //kotlin-jdsl
    val jdslVersion = "2.2.1.RELEASE"
    // base
    implementation("com.linecorp.kotlin-jdsl:hibernate-kotlin-jdsl-jakarta:$jdslVersion")
    implementation("org.hibernate:hibernate-core:6.2.4.Final") // up to 6
    // if using spring-data-jpa
    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-starter-jakarta:$jdslVersion")

//    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-data-redis")
//    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
//    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    dependsOn(tasks.test)
}
