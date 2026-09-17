plugins {
    kotlin("jvm")
    `maven-publish`
}

kotlin {
    jvmToolchain(25)
}

// Deliberately no publishing { publications { ... } } configuration.
