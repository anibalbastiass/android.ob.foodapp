package com.ob.foodapp.feature.signin

import com.ob.foodapp.feature.signin.data.dataModule
import com.ob.foodapp.feature.signin.domain.domainModule
import com.ob.foodapp.feature.signin.presentation.presentationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "FirebaseAuth"

internal val firebaseAuthModule = Kodein.Module("${MODULE_NAME}Module") {
    import(dataModule)
    import(domainModule)
    import(presentationModule)
}
