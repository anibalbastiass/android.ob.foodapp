plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    kotlin(GradlePluginId.ANDROID)
    kotlin(GradlePluginId.KAPT)
}

apply(from = "./../config/gradle/common-android-core-library.gradle")

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME + ".${project.versionBanner()}"
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }
}

kapt {
    generateStubs = true
}

dependencies {
    implementation(LibraryDependency.KOTLIN)
    implementation(TestLibraryDependency.ANDROID_X_CORE_TESTING)
    implementation(TestLibraryDependency.JUNIT_API)
    runtimeOnly(TestLibraryDependency.JUNIT_ENGINE)
    implementation(TestLibraryDependency.JUNIT_PARAMS)
    implementation(TestLibraryDependency.JUNIT4)
    runtimeOnly(TestLibraryDependency.JUNIT_VINTAGE)
    implementation(TestLibraryDependency.COROUTINES_TEST)
}
