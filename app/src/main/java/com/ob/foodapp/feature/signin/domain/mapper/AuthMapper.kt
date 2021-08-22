package com.ob.foodapp.feature.signin.domain.mapper

import com.google.firebase.auth.FirebaseUser
import com.ob.foodapp.feature.signin.domain.model.DomainUser

class AuthMapper {

    fun FirebaseUser.fromFirebaseToDomain() = DomainUser(
        uid = uid,
        name = displayName ?: "",
        email = email ?: ""
    )
}
