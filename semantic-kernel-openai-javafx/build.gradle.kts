plugins {
    application
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.hibernate.orm") version "6.5.2.Final"
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.15.0")

    implementation("ch.qos.logback:logback-classic:1.5.6")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.14")

    implementation("org.openjfx:javafx:22.0.2")
    implementation("org.openjfx:javafx-fxml:22.0.2")

    implementation("com.google.inject:guice:7.0.0")

    implementation("com.h2database:h2:2.2.224")
    implementation("org.hibernate:hibernate:3.5.4-Final")
    implementation("org.hibernate:hibernate-core:6.5.2.Final")
    implementation("org.flywaydb:flyway-core:10.16.0")

    implementation("com.microsoft.semantic-kernel:semantickernel-api:1.1.5")
    implementation("com.microsoft.semantic-kernel:semantickernel-bom:1.1.5")
    implementation("com.microsoft.semantic-kernel:semantickernel-aiservices-openai:1.1.5")

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

tasks.test {
    useJUnitPlatform()
}
