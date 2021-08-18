package com.ob.foodapp.feature.signin.domain.usecase

import com.ob.foodapp.feature.signin.domain.model.DomainAuth
import com.ob.foodapp.feature.signin.domain.repository.RemoteAuthRepository

class SignInUseCase(private val repository: RemoteAuthRepository) {

    suspend fun execute(email: String, password: String, onCompletedBlock: (DomainAuth) -> Unit) {
        repository.signIn(email, password, onCompletedBlock)
    }
}