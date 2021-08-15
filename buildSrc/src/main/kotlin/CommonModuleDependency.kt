import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addFirebaseDependencies() {
    implementation(platform(LibraryDependency.FIREBASE_BOM))
    implementation(LibraryDependency.FIREBASE_AUTH)
    implementation(LibraryDependency.FIREBASE_FIRE_STORE)
    implementation(LibraryDependency.FIREBASE_CRASHLYTICS)
    implementation(LibraryDependency.FIREBASE_ANALYTICS)
    implementation(LibraryDependency.FIREBASE_PERFORMANCE)
}

fun DependencyHandler.addCommonUIDependencies() {
    implementation(LibraryDependency.SUPPORT_CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.COORDINATOR_LAYOUT)
    implementation(LibraryDependency.RECYCLER_VIEW)
    implementation(LibraryDependency.MATERIAL)
    implementation(LibraryDependency.SWIPE_REFRESH_LAYOUT)
    implementation(LibraryDependency.FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_UI_KTX)
    implementation(LibraryDependency.APP_COMPAT)
    implementation(LibraryDependency.CORE_KTX)

    implementation(LibraryDependency.TIMBER)

    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.KOTLIN_REFLECT)
    implementation(LibraryDependency.KODEIN)
    implementation(LibraryDependency.KODEIN_ANDROID_X)

    implementation(LibraryDependency.COROUTINES_ANDROID)
    implementation(LibraryDependency.COROUTINES_CORE)

    implementation(LibraryDependency.LIFECYCLE_VIEW_MODEL_KTX)
    kapt(LibraryDependency.LIFECYCLE_COMPILER)
}

fun DependencyHandler.addTestDependencies() {
    testImplementation(project(ModuleDependency.LIBRARY_TEST_CORE))

    // Unit Tests
    testRuntimeOnly(TestLibraryDependency.JUNIT_ENGINE)
    testRuntimeOnly(TestLibraryDependency.JUNIT_VINTAGE)
    testImplementation(TestLibraryDependency.JUNIT_API)
    testImplementation(TestLibraryDependency.JUNIT_PARAMS)
    testImplementation(TestLibraryDependency.KLUENT)
    testImplementation(TestLibraryDependency.MOCKK)
    testImplementation(TestLibraryDependency.MOCKITO_CORE)
    testImplementation(TestLibraryDependency.MOCKITO_INLINE)
    testImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
    testImplementation(TestLibraryDependency.COROUTINES_TEST)
    testImplementation(TestLibraryDependency.ANDROID_X_CORE_TESTING)
    testImplementation(TestLibraryDependency.ROOM_TESTING)
    testImplementation(TestLibraryDependency.ROBOLECTRIC)
    testImplementation(TestLibraryDependency.ANDROID_X_TEST_CORE)
    testImplementation(TestLibraryDependency.TURBINE)

    // Instrumental Tests
    androidTestImplementation(TestLibraryDependency.TEST_RUNNER)
    androidTestImplementation(TestLibraryDependency.TEST_RULES)
    androidTestImplementation(TestLibraryDependency.ESPRESSO_CORE)
    androidTestImplementation(TestLibraryDependency.KLUENT_ANDROID)
    androidTestImplementation(TestLibraryDependency.MOCKITO_ANDROID)
}

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.testRuntimeOnly(dependencyNotation: Any): Dependency? =
    add("testRuntimeOnly", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }

    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T
