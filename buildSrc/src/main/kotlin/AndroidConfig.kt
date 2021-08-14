import org.gradle.api.Project
import java.io.ByteArrayOutputStream

object AndroidConfig {
    const val COMPILE_SDK_VERSION = 30
    const val MIN_SDK_VERSION = 22
    const val TARGET_SDK_VERSION = 30
    const val BUILD_TOOLS_VERSION = "30.0.2"

    // Version Name and code
    private const val versionMajor = 0
    private const val versionMinor = 1
    private const val versionPatch = 0

    internal val currentVersion = SemanticVersion(
        major = versionMajor,
        minor = versionMinor,
        patch = versionPatch,
        preRelease = SemanticVersion.SNAPSHOT_TYPE
    )
    val VERSION_NAME = currentVersion.toString()
    const val VERSION_CODE = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100

    const val ID = "com.ob.foodapp"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

fun Project.versionBanner(): String {
    val os = ByteArrayOutputStream()
    project.exec {
        commandLine = "git rev-parse --verify --short HEAD".split(" ")
        standardOutput = os
    }
    return String(os.toByteArray()).trim() + AndroidConfig.currentVersion.preRelease?.let { preRelease -> "-$preRelease" }
}

fun Project.isCI(): Boolean {
    return try {
        (System.getenv("ci") ?: project.properties["ci"]).toString().toBoolean()
    } catch (e: NullPointerException) {
        false
    }
}

data class SemanticVersion(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val preRelease: String? = null,
    val buildMetaData: String? = null
) {

    companion object {
        const val SNAPSHOT_TYPE = "SNAPSHOT"
        const val ALPHA_TYPE = "alpha%s"
        const val BETA_TYPE = "beta%s"
        const val RELEASE_CANDIDATE_TYPE = "rc%s"
        const val RELEASE_TYPE = ""
    }

    override fun toString() = buildString {
        append("$major.$minor.$patch")
//        preRelease?.let { append("-$preRelease") }
        buildMetaData?.let { append("+$buildMetaData") }
    }
}
