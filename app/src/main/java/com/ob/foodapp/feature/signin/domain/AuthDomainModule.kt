package com.ob.foodapp.feature.signin.domain

import com.ob.foodapp.feature.signin.MODULE_NAME
import com.ob.foodapp.feature.signin.domain.usecase.SignInUseCase
import com.ob.foodapp.feature.signin.domain.usecase.SignOutUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { SignInUseCase(instance()) }

    bind() from singleton { SignOutUseCase(instance()) }

}