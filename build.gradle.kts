import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.8.0"

    id("org.springframework.boot") version "2.7.11"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

}

group = "com.coludclub"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

object DependencyVersions {
    const val SLACK_CLIENT_VERSION = "1.8.1"
    const val KOTEST_VERSION = "5.4.2"
    const val QUARTZ_VERSION = "2.3.2"
}

dependencies {
    /** Spring Boot starter (kotlin, web, validation) */
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-quartz")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")

    kapt("org.springframework.boot:spring-boot-configuration-processor")

    implementation("io.github.microutils:kotlin-logging:1.12.0")


    /** Scheduler */
    implementation("org.quartz-scheduler:quartz:${DependencyVersions.QUARTZ_VERSION}")

    /** Serialize */
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    /** slack */
    implementation("com.slack.api:slack-api-client:${DependencyVersions.SLACK_CLIENT_VERSION}")
    implementation("com.slack.api:slack-api-model-kotlin-extension:${DependencyVersions.SLACK_CLIENT_VERSION}")
    implementation("com.slack.api:slack-api-client-kotlin-extension:${DependencyVersions.SLACK_CLIENT_VERSION}")

    /** Test */
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:${DependencyVersions.KOTEST_VERSION}")
    testImplementation("io.kotest:kotest-framework-datatest:${DependencyVersions.KOTEST_VERSION}")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
