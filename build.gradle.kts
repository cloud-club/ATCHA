import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.11"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.coludclub"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

object DependencyVersions {
//	const val SLACK_WEBHOOK_VERSION = "1.4.0"
	const val SLACK_CLIENT_VERSION = "1.8.1"
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


	/** slack */
//	implementation("net.gpedro.integrations.slack:slack-webhook:${DependencyVersions.SLACK_WEBHOOK_VERSION}")
	implementation("com.slack.api:slack-api-client:${DependencyVersions.SLACK_CLIENT_VERSION}")
	implementation("com.slack.api:slack-api-model-kotlin-extension:${DependencyVersions.SLACK_CLIENT_VERSION}")
	implementation("com.slack.api:slack-api-client-kotlin-extension:${DependencyVersions.SLACK_CLIENT_VERSION}")
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
