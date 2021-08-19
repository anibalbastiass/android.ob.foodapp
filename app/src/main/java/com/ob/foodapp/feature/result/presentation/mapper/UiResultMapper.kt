package com.ob.foodapp.feature.result.presentation.mapper

import com.ob.foodapp.feature.result.domain.model.DomainResultItem
import com.ob.foodapp.feature.result.presentation.model.UiResultItem
import com.ob.foodapp.feature.signin.domain.model.DomainUser
import com.ob.foodapp.feature.signin.presentation.model.UiUser

class UiResultMapper {

    fun DomainResultItem.fromDomainToUi() = UiResultItem(
        id = id ?: "",
        imageUrl = imageUrl ?: "",
        name = name ?: "",
        price = price ?: 0,
        rating = rating ?: 0f
    )
}