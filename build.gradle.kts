plugins {
	java
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.jerik-the-dog"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// now we'll add the openapi dependency
	// https://swagger.io/specification/
	// what is swagger and what is openapi:
	// openapi == specification
	// swagger == tools to implement that specification ^^^
	// https://swagger.io/blog/api-strategy/difference-between-swagger-and-openapi/

	// SPRING BOOT 2 !!!
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
	// this will give us an out-of-the-box swagger UI: localhost:8080/swagger-ui.html
	//implementation("org.springdoc:springdoc-openapi-ui:1.7.0")

	// !!! it's a bit different in spring boot 3
	// https://stackoverflow.com/questions/74701738/spring-boot-3-springdoc-openapi-ui-doesnt-work
	// https://springdoc.org/v2/#features
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

	// THERE'S A REACTIVE STARTER TOO!!

	// http://localhost:8080/v3/api-docs
	// http://localhost:8080/swagger-ui/index.html
}

tasks.withType<Test> {
	useJUnitPlatform()
}
