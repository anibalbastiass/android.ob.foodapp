package com.ob.foodapp.feature.profile.domain.usecase

import com.ob.foodapp.feature.profile.domain.model.DomainProfile
import com.ob.foodapp.feature.profile.domain.repository.RemoteProfileRepository

class UpdateProfileUseCase(private val repository: RemoteProfileRepository) {

    suspend fun execute(
        profile: DomainProfile,
        onCompletedBlock: (Boolean) -> Unit
    ) {
        repository.updateProfile(profile, onCompletedBlock)
    }

}