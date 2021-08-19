package com.ob.foodapp.feature.result.presentation.model

data class UiResultItem(
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Long,
    val rating: Float
)
