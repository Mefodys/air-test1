import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.SourceSetContainer

plugins {
    kotlin("jvm") version "2.4.20"
}

repositories {
    mavenCentral()
}

val extra = the<SourceSetContainer>().create("extra") {
    kotlin.srcDir("src/extra/kotlin")
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.moduleName.set("duplicate-module")
}

tasks.jar {
    dependsOn("compileExtraKotlin")
    from(layout.buildDirectory.dir("classes/kotlin/extra"))
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
