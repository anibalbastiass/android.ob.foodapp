package com.ob.foodapp.feature.profile.presentation.mapper

import com.ob.foodapp.feature.profile.domain.model.DomainProfile
import com.ob.foodapp.feature.profile.presentation.model.UiProfile

class UiProfileMapper {

    fun DomainProfile.fromDomainToUi() = UiProfile(
        id = id ?: "",
        name = name ?: "",
        email = email ?: "",
        city = city ?: "",
        bio = bio ?: "",
        avatarUrl = avatarUrl ?: "",
        likes = likes ?: arrayListOf()
    )

    fun UiProfile.fromUiToDomain(userId: String) = DomainProfile(
        id = userId,
        name = name,
        email = email,
        city = city,
        bio = bio,
        avatarUrl = avatarUrl,
        likes = likes
    )
}