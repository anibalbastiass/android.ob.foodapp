package com.ob.foodapp.feature.signin.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.ob.foodapp.feature.signin.domain.mapper.AuthMapper
import com.ob.foodapp.feature.signin.domain.model.DomainAuth
import com.ob.foodapp.feature.signin.domain.repository.RemoteAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteAuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val authMapper: AuthMapper
) : RemoteAuthRepository {

    override suspend fun signIn(
        email: String,
        password: String,
        onCompletedBlock: (DomainAuth) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser

                user?.let { nonNullUser ->
                    with(authMapper) {
                        onCompletedBlock.invoke(DomainAuth(user = nonNullUser.fromFirebaseToDomain()))
                    }
                } ?: run {
                    onCompletedBlock.invoke(DomainAuth(exception = task.exception))
                }
            } else {
                onCompletedBlock.invoke(DomainAuth(exception = task.exception))
            }
        }
    }

    override suspend fun signOut(): Flow<Boolean> = flow {
        firebaseAuth.signOut()
        emit(true)
    }
}