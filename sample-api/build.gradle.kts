import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("kapt")
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("com.anddani.sampleapi.MainKt")
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.kotlin.result)
    implementation(libs.dagger.api)
    implementation(libs.hikari)
    kapt(libs.dagger.compiler)

    implementation(project(":common"))
    implementation(project(":client:smt"))
    implementation(project(":repository:smt"))
    implementation(project(":service:smt"))
}
