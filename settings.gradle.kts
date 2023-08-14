enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "kotlin-backend-sample"

include(":sample-api")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
