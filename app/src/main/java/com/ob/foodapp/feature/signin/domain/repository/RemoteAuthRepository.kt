package com.ob.foodapp.feature.signin.domain.repository

import com.ob.foodapp.feature.signin.domain.model.DomainAuth
import kotlinx.coroutines.flow.Flow

interface RemoteAuthRepository {
    suspend fun signIn(
        email: String,
        password: String,
        onCompletedBlock: (DomainAuth) -> Unit
    )

    suspend fun signOut(): Flow<Boolean>
}