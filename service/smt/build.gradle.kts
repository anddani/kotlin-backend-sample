import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("kapt")
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":client:smt"))
    implementation(project(":repository:smt"))
    implementation(libs.kotlin.result)
    implementation(libs.dagger.api)
    kapt(libs.dagger.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.coroutines.test)
}
