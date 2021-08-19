package com.ob.foodapp.feature.result.domain.repository

import com.ob.foodapp.feature.result.domain.model.DomainResultItem

interface RemoteResultRepository {
    suspend fun getItems(
        category: String,
        onCompletedBlock: (MutableList<DomainResultItem>) -> Unit
    )
}