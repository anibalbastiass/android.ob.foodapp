package com.ob.foodapp.feature.result.domain.usecase

import com.ob.foodapp.feature.result.domain.model.DomainResultItem
import com.ob.foodapp.feature.result.domain.repository.RemoteResultRepository

class GetItemsUseCase(private val repository: RemoteResultRepository) {

    suspend fun execute(
        category: String,
        onCompletedBlock: (MutableList<DomainResultItem>) -> Unit
    ) {
        repository.getItems(category, onCompletedBlock)
    }

}