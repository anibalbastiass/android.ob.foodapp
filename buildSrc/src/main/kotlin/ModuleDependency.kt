import kotlin.reflect.full.memberProperties


@Suppress("unused")
object ModuleDependency {
    const val APP = ":app"
    const val LIBRARY_TEST_CORE = ":library:test-core"
    const val LIBRARY_UI_CORE = ":library:ui-core"
    const val LIBRARY_MVI_CORE = ":library:mvi-core"

    fun getAllModules() = ModuleDependency::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

}
