package com.ob.foodapp.feature.profile.domain.model

data class DomainProfile(
    val id: String? = "",
    val name: String? = "",
    val email: String? = "",
    val city: String? = "",
    val bio: String? = "",
    val avatarUrl: String? = "",
    val likes: MutableList<String>? = arrayListOf()
)