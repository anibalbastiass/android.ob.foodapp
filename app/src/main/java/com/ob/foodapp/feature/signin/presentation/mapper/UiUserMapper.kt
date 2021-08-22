package com.ob.foodapp.feature.signin.presentation.mapper

import com.ob.foodapp.feature.signin.domain.model.DomainUser
import com.ob.foodapp.feature.signin.presentation.model.UiUser

class UiUserMapper {

    fun DomainUser.fromDomainToUi() = UiUser(
        uid = uid,
        name = name,
        email = email
    )
}