import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    api(libs.kotlin.result)
    api(libs.kotlin.result.coroutines)
    api(libs.coroutines.core)
    implementation(libs.ktor.serialization)
}
