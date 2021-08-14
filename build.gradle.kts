import com.android.build.gradle.BaseExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(GradlePluginId.ANDROID_APPLICATION) version GradlePluginVersion.ANDROID_GRADLE apply false
    id(GradlePluginId.ANDROID_LIBRARY) version GradlePluginVersion.ANDROID_GRADLE apply false
    kotlin(GradlePluginId.ANDROID) version GradlePluginVersion.KOTLIN apply false

    id(GradlePluginId.SAFE_ARGS) version GradlePluginVersion.SAFE_ARGS apply false
    id(GradlePluginId.GRADLE_VERSION_PLUGIN) version GradlePluginVersion.GRADLE_VERSION_PLUGIN

    id(GradlePluginId.DETEKT) version GradlePluginVersion.DETEKT

    id(GradlePluginId.GOOGLE_SERVICES) version GradlePluginVersion.GOOGLE_SERVICES apply false
    id(GradlePluginId.FIREBASE_CRASHLYTICS) version GradlePluginVersion.FIREBASE_CRASHLYTICS apply false
    id(GradlePluginId.FIREBASE_PERF) version GradlePluginVersion.FIREBASE_PERF apply false

    id(GradlePluginId.JUNIT5) version GradlePluginVersion.JUNIT5 apply false
    id(GradlePluginId.COVERALLS) version GradlePluginVersion.COVERALLS apply false
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://jitpack.io")
    }
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    apply(plugin = GradlePluginId.DETEKT)

    detekt {
        config = files("${project.rootDir}/config/detekt/detekt.yml")
        parallel = true
    }
    afterEvaluate {
        configureAndroid()
    }
}

fun Project.configureAndroid() {
    (project.extensions.findByName("android") as? BaseExtension)?.run {
        sourceSets {
            map { it.java.srcDir("src/${it.name}/kotlin") }
        }
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String) = "^[0-9,.v-]+(-r)?$".toRegex().matches(version).not()

// JVM target applied to all Kotlin tasks across all sub-projects
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    kotlinOptions.useIR = true
}

task("staticCheck") {
    description =
        """Mimics all static checks that run on CI.
        Note that this task is intended to run locally (not on CI), because on CI we prefer to have parallel execution
        and separate reports for each check (multiple statuses eg. on github PR page).
        """.trimMargin()

    group = "verification"
    afterEvaluate {
        // Filter modules with "lintDebug" task (non-Android modules do not have lintDebug task)
        val lintTasks = subprojects.mapNotNull { "${it.name}:lintDebug" }

        // Get modules with "testDebugUnitTest" task (app module does not have it)
        val testTasks = subprojects.mapNotNull { "${it.name}:testDebugUnitTest" }
            .filter { it != "app:testDebugUnitTest" }

        // All task dependencies
        val taskDependencies =
            mutableListOf("app:assembleAndroidTest", "detekt").also {
                it.addAll(lintTasks)
                it.addAll(testTasks)
            }

        // By defining Gradle dependency all dependent tasks will run before this "empty" task
        dependsOn(taskDependencies)
    }
}
buildscript {
    val kotlin_version by extra("1.4.32")
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}
