package com.ob.foodapp.feature.profile.domain.usecase

import com.ob.foodapp.feature.profile.domain.model.DomainProfile
import com.ob.foodapp.feature.profile.domain.repository.RemoteProfileRepository

class GetProfileUseCase(private val repository: RemoteProfileRepository) {

    suspend fun execute(
        uid: String,
        onCompletedBlock: (DomainProfile) -> Unit
    ) {
        repository.getProfile(uid, onCompletedBlock)
    }

}