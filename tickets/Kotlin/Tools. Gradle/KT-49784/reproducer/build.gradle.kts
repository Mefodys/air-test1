import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.api.publish.maven.MavenPublication
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.4.20"
    `java-library`
    `maven-publish`
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
}

val commonSourceSet = sourceSets.create("common")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    registerFeature("common") {
        usingSourceSet(commonSourceSet)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven { url = uri(layout.buildDirectory.dir("repo")) }
    }
}

tasks.register("verifyPublishedMetadata") {
    dependsOn("publishMavenJavaPublicationToMavenRepository")
    doLast {
        val metadata = layout.buildDirectory.file("repo/org/example/kt-49784-reproducer/1.0/kt-49784-reproducer-1.0.module").get().asFile
        val text = metadata.readText()
        val main = "org.gradle.jvm.environment\\\": \\\"standard-jvm\\\""
        val common = "commonApiElements"
        check(text.contains(main)) { "Main variants did not contain the JVM environment attribute." }
        check(text.contains(common)) { "The common feature was not published." }
        println("Published metadata contains the common feature; inspect the .module file for its variant attributes.")
    }
}
