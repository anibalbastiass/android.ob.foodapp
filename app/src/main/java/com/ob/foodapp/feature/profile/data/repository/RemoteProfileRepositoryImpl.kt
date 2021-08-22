package com.ob.foodapp.feature.profile.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.ob.foodapp.feature.profile.domain.model.DomainProfile
import com.ob.foodapp.feature.profile.domain.repository.RemoteProfileRepository

class RemoteProfileRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : RemoteProfileRepository {

    init {
        FirebaseFirestore.setLoggingEnabled(true)
    }

    override suspend fun getProfile(
        uid: String,
        onCompletedBlock: (DomainProfile) -> Unit
    ) {
        fireStore
            .collection("users")
            .document(uid)
            .addSnapshotListener { value, error ->
                val profile: DomainProfile?

                if (value != null) {
                    profile = value.toObject(DomainProfile::class.java)

                    profile?.let {
                        onCompletedBlock.invoke(profile)
                    }
                }
            }
    }

}