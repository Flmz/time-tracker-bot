import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.21"
    id("io.ktor.plugin") version "3.0.1"
    kotlin("plugin.serialization") version "2.0.21"
}

group = "ru.flmz"
version = "0.0.1"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation("dev.inmo:tgbotapi:20.0.0")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("org.jetbrains.exposed:exposed-core:0.56.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.56.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.56.0")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.insert-koin:koin-core:4.0.0")
    implementation("org.flywaydb:flyway-core:10.21.0")
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.JVM_21)
    }
}
