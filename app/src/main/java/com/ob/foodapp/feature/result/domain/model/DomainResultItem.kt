package com.ob.foodapp.feature.result.domain.model

data class DomainResultItem(
    val id: String? = "",
    val imageUrl: String? = "",
    val name: String? = "",
    val price: Long? = 0,
    val rating: Float? = 0f
)
