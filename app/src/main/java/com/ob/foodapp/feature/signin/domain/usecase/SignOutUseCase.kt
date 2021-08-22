package com.ob.foodapp.feature.signin.domain.usecase

import com.ob.foodapp.feature.signin.domain.repository.RemoteAuthRepository
import kotlinx.coroutines.flow.Flow

class SignOutUseCase(private val repository: RemoteAuthRepository) {

    suspend fun execute(): Flow<Boolean> {
        return repository.signOut()
    }
}