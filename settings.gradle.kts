pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application" -> useModule(
                    "com.android.tools.build:gradle:4.2.0"
                )
                "com.android.library" -> useModule(
                    "com.android.tools.build:gradle:4.2.0"
                )
                "androidx.navigation.safeargs" -> useModule(
                    "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.4"
                )
                "org.jacoco" -> useModule("org.jacoco:org.jacoco.core:0.8.6")
                "org.jetbrains.dokka" -> useModule(
                    "org.jetbrains.dokka:dokka-gradle-plugin:1.4.10.2"
                )
                "com.google.gms.google-services" -> useModule(
                    "com.google.gms:google-services:4.3.10"
                )
                "com.google.firebase.crashlytics" -> useModule(
                    "com.google.firebase:firebase-crashlytics-gradle:2.5.1"
                )
                "com.google.firebase.firebase-perf" -> useModule(
                    "com.google.firebase:perf-plugin:1.3.5"
                )
                "de.mannodermaus.android-junit5" -> useModule(
                    "de.mannodermaus.gradle.plugins:android-junit5:1.7.1.1"
                )
            }
        }
    }
}

rootProject.name = "food_app"
rootProject.buildFileName = "build.gradle.kts"
include(
    "app",
    "library:test-core",
    "library:ui-core",
    "library:mvi-core"
)
