plugins {
    `kotlin-dsl`
}

group = "com.firstwords.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "firstwords.android.application"
            implementationClass = "com.firstwords.convention.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "firstwords.android.library"
            implementationClass = "com.firstwords.convention.AndroidLibraryConventionPlugin"
        }
    }
}