import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("kapt")
    id("app.cash.sqldelight") version "2.0.0"
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(project(":common"))
    implementation(libs.dagger.api)
    implementation(libs.hikari)
    kapt(libs.dagger.compiler)
    implementation("app.cash.sqldelight:jdbc-driver:2.0.0")
    implementation(libs.testcontainers)
    implementation(libs.postgres)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.coroutines.test)
    testImplementation("app.cash.sqldelight:jdbc-driver:2.0.0")
    testImplementation(libs.testcontainers)
    testImplementation(libs.postgres)
}

sqldelight {
    databases {
        create("SmtDb") {
            packageName.set("com.anddani")
            dialect("app.cash.sqldelight:postgresql-dialect:2.0.0")
            deriveSchemaFromMigrations.set(true)
        }
    }
}