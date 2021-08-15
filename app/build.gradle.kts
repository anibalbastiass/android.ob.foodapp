plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    kotlin(GradlePluginId.ANDROID)
    kotlin(GradlePluginId.KAPT)
    id(GradlePluginId.KOTLIN_PARCELIZE)
    id(GradlePluginId.SAFE_ARGS)

    id(GradlePluginId.GOOGLE_SERVICES)
    id(GradlePluginId.FIREBASE_CRASHLYTICS)
    id(GradlePluginId.FIREBASE_PERF)
    id(GradlePluginId.JUNIT5)
}

apply(from = "./../config/gradle/common-android-core-library.gradle")

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidConfig.ID
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME + ".${project.versionBanner()}"

        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildFeatures {
        viewBinding = true
    }
}

kapt {
    generateStubs = true
}

dependencies {
    implementation(project(ModuleDependency.LIBRARY_UI_CORE))
    implementation(project(ModuleDependency.LIBRARY_MVI_CORE))
    implementation(project(ModuleDependency.LIBRARY_TEST_CORE))

    addCommonUIDependencies()
    addFirebaseDependencies()
    addTestDependencies()
}

repositories {
    google()
}
