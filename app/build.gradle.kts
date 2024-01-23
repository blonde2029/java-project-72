import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("checkstyle")
    application
//    kotlin("plugin.lombok") version "1.9.21"
    id("io.freefair.lombok") version "8.3"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.github.ben-manes.versions") version "0.47.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("hexlet.code.App")
}
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.h2database:h2:2.2.222")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("org.apache.commons:commons-text:1.10.0")
    implementation("gg.jte:jte:3.1.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("io.javalin:javalin:5.6.2")
    implementation("io.javalin:javalin-bundle:5.6.2")
    implementation("io.javalin:javalin-rendering:5.6.2")
    implementation("org.postgresql:postgresql:42.7.1")
//    implementation("com.konghq:unirest-java-bom:4.0.12")
//    implementation("com.konghq:unirest-java-core")
//    implementation("com.konghq:unirest-object-mappers-gson")
//    implementation("com.konghq:unirest-objectmapper-jackson")
    implementation("com.konghq:unirest-java:3.14.1")
    implementation ("org.jsoup:jsoup:1.17.1")

    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}