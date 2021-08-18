package com.ob.foodapp.feature.signin.domain.model

data class DomainAuth(
    val user: DomainUser? = null,
    val exception: Exception? = null
)