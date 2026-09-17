plugins {
    kotlin("jvm") version "2.4.20"
    `java-library`
}

group = "project"
version = "1.0"

java {
    withSourcesJar()
}

repositories {
    mavenCentral()
}
