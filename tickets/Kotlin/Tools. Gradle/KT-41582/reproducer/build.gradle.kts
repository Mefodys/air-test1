plugins {
    kotlin("jvm") version "2.4.20"
    `maven-publish`
}

repositories {
    mavenCentral()
}

// Deliberately no `publishing { publications { ... } }` block.
// KT-41582 asks whether applying maven-publish to a JVM project should create
// the publication automatically, as the multiplatform plugin does.
