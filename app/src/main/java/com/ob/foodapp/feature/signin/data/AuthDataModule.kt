package com.ob.foodapp.feature.signin.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ob.foodapp.feature.signin.MODULE_NAME
import com.ob.foodapp.feature.signin.data.repository.RemoteAuthRepositoryImpl
import com.ob.foodapp.feature.signin.domain.mapper.AuthMapper
import com.ob.foodapp.feature.signin.domain.repository.RemoteAuthRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind() from singleton { Firebase.auth}

    bind<RemoteAuthRepository>() with singleton {
        RemoteAuthRepositoryImpl(
            instance(),
            instance()
        )
    }

    bind() from singleton { AuthMapper() }

    bind() from singleton { Firebase.auth.currentUser!! }
}