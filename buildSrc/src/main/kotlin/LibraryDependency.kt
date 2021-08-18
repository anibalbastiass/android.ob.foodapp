@file:Suppress("detekt.StringLiteralDuplication")

private object LibraryVersion {
    const val KODEIN = "6.5.5"
    const val TIMBER = "4.7.1"
    const val APP_COMPAT = "1.2.0"
    const val RECYCLER_VIEW = "1.1.0"
    const val COORDINATOR_LAYOUT = "1.1.0"
    const val MATERIAL = "1.2.0-beta01"
    const val SWIPE_REFRESH_LAYOUT = "1.0.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.3.2"
    const val FRAGMENT_KTX = "1.2.5"
    const val LIFECYCLE_VIEW_MODEL_KTX = "2.2.0"
    const val FIREBASE = "26.1.0"
    const val LIFECYCLE = "1.1.1"
}

object LibraryDependency {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${CoreVersion.KOTLIN}"

    // Required by Android dynamic feature modules and SafeArgs
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${CoreVersion.KOTLIN}"
    const val KODEIN = "org.kodein.di:kodein-di-generic-jvm:${LibraryVersion.KODEIN}"
    const val KODEIN_ANDROID_X = "org.kodein.di:kodein-di-framework-android-x:${LibraryVersion.KODEIN}"
    const val TIMBER = "com.jakewharton.timber:timber:${LibraryVersion.TIMBER}"
    const val SUPPORT_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${LibraryVersion.APP_COMPAT}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLER_VIEW}"
    const val COORDINATOR_LAYOUT =
        "androidx.coordinatorlayout:coordinatorlayout:${LibraryVersion.COORDINATOR_LAYOUT}"
    const val MATERIAL = "com.google.android.material:material:${LibraryVersion.MATERIAL}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${LibraryVersion.SWIPE_REFRESH_LAYOUT}"
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${CoreVersion.COROUTINES_ANDROID}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${CoreVersion.COROUTINES_ANDROID}"
    const val CORE_KTX = "androidx.core:core-ktx:${LibraryVersion.CORE_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${LibraryVersion.FRAGMENT_KTX}"
    const val LIFECYCLE_VIEW_MODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.LIFECYCLE_VIEW_MODEL_KTX}"
    const val LIFECYCLE_COMPILER =
        "androidx.lifecycle:lifecycle-compiler:${LibraryVersion.LIFECYCLE_VIEW_MODEL_KTX}"
    const val LIFECYCLE_EXTENSIONS = "android.arch.lifecycle:extensions:${LibraryVersion.LIFECYCLE}"
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${CoreVersion.NAVIGATION}"

    // Dynamic Feature Module Support
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${CoreVersion.NAVIGATION}"

    // Firebase
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${LibraryVersion.FIREBASE}"
    const val FIREBASE_AUTH = "com.google.firebase:firebase-auth-ktx"
    const val FIREBASE_FIRE_STORE = "com.google.firebase:firebase-firestore-ktx"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
    const val FIREBASE_PERFORMANCE = "com.google.firebase:firebase-perf-ktx"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
}
