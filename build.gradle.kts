plugins {
    id("java")
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("com.h2database:h2")
    implementation("org.flywaydb:flyway-core")

//    implementation("org.springframework.ai:spring-ai-bom:1.0.0-M1")
//    implementation("org.springframework.ai:spring-ai-ollama-spring-boot-starter")

    implementation("com.microsoft.semantic-kernel:semantickernel-api:1.1.5")
    implementation("com.microsoft.semantic-kernel:semantickernel-bom:1.1.5")
    implementation("com.microsoft.semantic-kernel:semantickernel-aiservices-openai:1.1.5")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}