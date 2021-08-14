private object TestLibraryVersion {
    const val JUNIT = "5.7.0"
    const val JUNIT4 = "4.13"
    const val KLUENT = "1.65"
    const val TEST_RUNNER = "1.1.0"
    const val ESPRESSO_CORE = "3.1.0"
    const val MOCKK = "1.10.6"
    const val ANDROID_X_TEST = "2.1.0"
    const val ROOM = "2.2.5"
    const val ROBOLECTRIC = "4.5.1"
    const val ANDROID_X_TEST_CORE = "1.0.0"
    const val MOCKITO = "3.8.0"
    const val MOCKITO_KOTLIN = "2.2.0"
    const val TURBINE = "0.4.1"
}

object TestLibraryDependency {
    const val JUNIT_API = "org.junit.jupiter:junit-jupiter-api:${TestLibraryVersion.JUNIT}"
    const val JUNIT_ENGINE = "org.junit.jupiter:junit-jupiter-engine:${TestLibraryVersion.JUNIT}"
    const val JUNIT_PARAMS = "org.junit.jupiter:junit-jupiter-params:${TestLibraryVersion.JUNIT}"
    const val JUNIT4 = "junit:junit:${TestLibraryVersion.JUNIT4}"
    const val JUNIT_VINTAGE = "org.junit.vintage:junit-vintage-engine:${TestLibraryVersion.JUNIT}"

    const val KLUENT_ANDROID = "org.amshove.kluent:kluent-android:${TestLibraryVersion.KLUENT}"
    const val KLUENT = "org.amshove.kluent:kluent:${TestLibraryVersion.KLUENT}"
    const val TEST_RUNNER = "androidx.test:runner:${TestLibraryVersion.TEST_RUNNER}"
    const val TEST_RULES = "androidx.test:rules:${TestLibraryVersion.TEST_RUNNER}"
    const val ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${TestLibraryVersion.ESPRESSO_CORE}"
    const val MOCKK = "io.mockk:mockk:${TestLibraryVersion.MOCKK}"
    const val MOCKITO_ANDROID = "org.mockito:mockito-android:${TestLibraryVersion.MOCKITO}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${TestLibraryVersion.MOCKITO}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${TestLibraryVersion.MOCKITO}"
    const val MOCKITO_KOTLIN =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${TestLibraryVersion.MOCKITO_KOTLIN}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${CoreVersion.COROUTINES_ANDROID}"
    const val ANDROID_X_CORE_TESTING =
        "androidx.arch.core:core-testing:${TestLibraryVersion.ANDROID_X_TEST}"
    const val ROOM_TESTING = "androidx.room:room-testing:${TestLibraryVersion.ROOM}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${TestLibraryVersion.ROBOLECTRIC}"
    const val ANDROID_X_TEST_CORE = "androidx.test:core:${TestLibraryVersion.ANDROID_X_TEST_CORE}"
    const val TURBINE = "app.cash.turbine:turbine:${TestLibraryVersion.TURBINE}"
}
