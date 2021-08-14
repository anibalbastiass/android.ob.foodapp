import kotlin.reflect.full.memberProperties


@Suppress("unused")
object ModuleDependency {
    const val APP = ":app"
    const val TEST_UTILS = ":testutils"
    const val CLIENT_STORAGE = ":clientstorage"
//    const val LIBRARY_UI_CORE = ":library:ui-core"

    fun getAllModules() = ModuleDependency::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

}
