enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "kotlin-backend-sample"

include(":common")
include(":sample-api")
include(":client:smt")
include(":service:smt")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
