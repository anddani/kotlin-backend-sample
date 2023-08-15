enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "kotlin-backend-sample"

include(":client:smt")
include(":common")
include(":repository:smt")
include(":sample-api")
include(":service:smt")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
