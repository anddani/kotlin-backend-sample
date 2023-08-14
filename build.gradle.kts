import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "org.sample"
version = "1.0"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
