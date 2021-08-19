package com.ob.foodapp.feature.result

import com.ob.foodapp.feature.result.data.dataModule
import com.ob.foodapp.feature.result.domain.domainModule
import com.ob.foodapp.feature.result.presentation.presentationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "FirebaseResult"

internal val firebaseResultModule = Kodein.Module("${MODULE_NAME}Module") {
    import(dataModule)
    import(domainModule)
    import(presentationModule)
}
