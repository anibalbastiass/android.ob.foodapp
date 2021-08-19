package com.ob.foodapp.feature.profile.domain

import com.ob.foodapp.feature.profile.domain.usecase.GetProfileUseCase
import com.ob.foodapp.feature.result.MODULE_NAME
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { GetProfileUseCase(instance()) }

}
