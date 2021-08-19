package com.ob.foodapp.feature.profile.data

import com.ob.foodapp.feature.profile.MODULE_NAME
import com.ob.foodapp.feature.profile.data.repository.RemoteProfileRepositoryImpl
import com.ob.foodapp.feature.profile.domain.repository.RemoteProfileRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

//    bind() from singleton { Firebase.firestore }

    bind<RemoteProfileRepository>() with singleton {
        RemoteProfileRepositoryImpl(
            instance()
        )
    }
}
