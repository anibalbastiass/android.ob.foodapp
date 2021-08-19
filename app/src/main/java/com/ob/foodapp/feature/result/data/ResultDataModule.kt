package com.ob.foodapp.feature.result.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ob.foodapp.feature.result.data.repository.RemoteResultRepositoryImpl
import com.ob.foodapp.feature.result.domain.repository.RemoteResultRepository
import com.ob.foodapp.feature.result.MODULE_NAME
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind() from singleton { Firebase.firestore }

    bind<RemoteResultRepository>() with singleton {
        RemoteResultRepositoryImpl(
            instance()
        )
    }
}
