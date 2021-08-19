package com.ob.foodapp.feature.profile.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.ob.foodapp.feature.profile.domain.model.DomainProfile
import com.ob.foodapp.feature.profile.domain.repository.RemoteProfileRepository
import com.ob.foodapp.feature.result.domain.model.DomainResultItem
import com.ob.foodapp.feature.result.domain.repository.RemoteResultRepository

class RemoteProfileRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : RemoteProfileRepository {

    init {
        FirebaseFirestore.setLoggingEnabled(true)
    }

    override suspend fun getProfile(
        email: String,
        onCompletedBlock: (DomainProfile) -> Unit
    ) {
        fireStore
            .collection("items")
            .whereEqualTo("email", email)
            .limit(1)
            .addSnapshotListener { value, error ->
                val profile: DomainProfile?

                if (value != null) {
                    profile = value.first().toObject(DomainProfile::class.java)

                    profile.let {
                        onCompletedBlock.invoke(profile)
                    }
                }
            }
    }

}