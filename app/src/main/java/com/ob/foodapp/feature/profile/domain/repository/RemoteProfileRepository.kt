package com.ob.foodapp.feature.profile.domain.repository

import com.ob.foodapp.feature.profile.domain.model.DomainProfile
import kotlinx.coroutines.flow.Flow

interface RemoteProfileRepository {
    suspend fun getProfile(uid: String, onCompletedBlock: (DomainProfile) -> Unit)
    suspend fun updateProfile(profile: DomainProfile, onCompletedBlock: (Boolean) -> Unit)
}