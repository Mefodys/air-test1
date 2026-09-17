import org.gradle.api.tasks.compile.JavaCompile

plugins {
    kotlin("jvm") version "2.4.20"
}

repositories {
    mavenCentral()
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xjdk-release=17")
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(17)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(26))
    }
}
