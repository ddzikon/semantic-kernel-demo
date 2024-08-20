plugins {
    application
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

extra["springAiVersion"] = "1.0.0-M1"

dependencies {
    implementation("org.apache.commons:commons-lang3:3.15.0")

    implementation("ch.qos.logback:logback-classic:1.5.6")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.openjfx:javafx:22.0.2")
    implementation("org.openjfx:javafx-fxml:22.0.2")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    implementation("org.flywaydb:flyway-core")
    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-devtools")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testCompileOnly("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
}

javafx {
    version = "22.0.1"
    modules = listOf( "javafx.controls", "javafx.fxml" )
}

application {
    mainClass = "org.example.Main"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
    }
}

tasks.test {
    useJUnitPlatform()
}
