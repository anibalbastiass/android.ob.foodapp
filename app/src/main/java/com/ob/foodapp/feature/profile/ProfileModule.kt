package com.ob.foodapp.feature.profile

import com.ob.foodapp.feature.profile.data.dataModule
import com.ob.foodapp.feature.profile.domain.domainModule
import com.ob.foodapp.feature.profile.presentation.presentationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "FirebaseProfile"

internal val firebaseProfileModule = Kodein.Module("${MODULE_NAME}Module") {
    import(dataModule)
    import(domainModule)
    import(presentationModule)
}
