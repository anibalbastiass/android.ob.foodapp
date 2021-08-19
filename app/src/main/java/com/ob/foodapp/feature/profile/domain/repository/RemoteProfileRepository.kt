package com.ob.foodapp.feature.profile.domain.repository

import com.ob.foodapp.feature.profile.domain.model.DomainProfile

interface RemoteProfileRepository {
    suspend fun getProfile(email: String, onCompletedBlock: (DomainProfile) -> Unit)
}