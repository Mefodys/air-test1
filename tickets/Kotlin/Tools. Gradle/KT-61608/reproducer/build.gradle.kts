import org.jetbrains.kotlin.gradle.plugin.KotlinBaseApiPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "2.4.20"
}

repositories {
    mavenCentral()
}

val standardCompile = tasks.named<KotlinJvmCompile>("compileKotlin")
val customCompile = plugins
    .getPlugin(KotlinBaseApiPlugin::class.java)
    .registerKotlinJvmCompileTask("customCompile")

customCompile.configure {
    // The ordinary source collection is exposed and can be copied.
    source(standardCompile.map { it.sources })

    // KT-61608: these inputs should also be available through public API,
    // without suppressing INVISIBLE_MEMBER or casting to an internal type.
    source(standardCompile.map { it.scriptSources })
    commonSourceSet.from(standardCompile.map { it.commonSourceSet })
}
