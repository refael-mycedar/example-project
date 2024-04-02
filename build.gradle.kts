import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
	id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
	id("org.flywaydb.flyway") version "7.7.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.mariadb.jdbc:mariadb-java-client:2.7.4")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("javax.validation:validation-api:2.0.1.Final")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

//tasks.withType<Test> {
//	useJUnitPlatform()
//}

flyway {
	url = "jdbc:mariadb://localhost:3306/users"
	user = "rafi_test"
	password = "pass"
}
