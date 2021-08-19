package com.ob.foodapp.feature.result.domain

import com.ob.foodapp.feature.result.MODULE_NAME
import com.ob.foodapp.feature.result.domain.usecase.GetItemsUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { GetItemsUseCase(instance()) }

}
