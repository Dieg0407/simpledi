plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation("org.assertj:assertj-core:3.25.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    api(libs.commons.math3)
    implementation(libs.guava)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events("passed", "failed", "skipped")

        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.SHORT
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}
