import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	kotlin("plugin.noarg") version "1.8.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

// Entity 작성하기
noArg {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}




dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	// springdoc 설치
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	// 트랜잭션이 담긴 패키지 jpa 추가
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// 레포지토리 사용하기 위해 h2 db 추가 // DB 연결하면 안써도 됨
	//implementation("com.h2database:h2")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation(kotlin("script-runtime"))

	// 어플리케이션이 실행될 때만 DB 드라이버를 설치하겠다.
	runtimeOnly("org.postgresql:postgresql")

	// aop 패키지 설정
	implementation("org.springframework.boot:spring-boot-starter-aop")
	// Spring Security 추가
	implementation("org.springframework.boot:spring-boot-starter-security")
	// jwt 관련 라이브러리 중 jjwt 추가
	implementation("io.jsonwebtoken:jjwt-api:0.12.3")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
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
