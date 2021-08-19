package com.ob.foodapp.feature.profile.presentation.model

data class UiProfile(
    val id: String,
    val name: String,
    val email: String,
    val city: String,
    val bio: String,
    val avatarUrl: String,
    val likes: MutableList<String>
)