package com.ob.foodapp.feature.result.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.ob.foodapp.feature.result.domain.model.DomainResultItem
import com.ob.foodapp.feature.result.domain.repository.RemoteResultRepository

class RemoteResultRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : RemoteResultRepository {

    init {
        FirebaseFirestore.setLoggingEnabled(true)
    }

    override suspend fun getItems(
        category: String,
        onCompletedBlock: (MutableList<DomainResultItem>) -> Unit
    ) {
        val query = when (category) {
            "All" -> {
                fireStore
                    .collection("items")
                    .orderBy("name", Query.Direction.ASCENDING)
            }
            else -> {
                fireStore
                    .collection("items")
                    .whereArrayContains("categories", category)
                    .orderBy("name", Query.Direction.ASCENDING)
            }
        }

        query.addSnapshotListener { value, error ->
            val resultList: MutableList<DomainResultItem> = ArrayList()
            if (value != null) {
                for (doc in value) {
                    if (doc != null) {
                        resultList.add(doc.toObject(DomainResultItem::class.java))
                    }
                }
                onCompletedBlock.invoke(resultList)
            }
        }
    }

}