import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.4.20"
}

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()
}

tasks.withType<KotlinCompile>().configureEach {
    // This is the error-prone replacement assignment reported in KT-41985.
    @Suppress("DEPRECATION")
    kotlinOptions.freeCompilerArgs = listOf("-Xcontext-parameters")
}
