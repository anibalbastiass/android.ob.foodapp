package com.ob.foodapp.feature.result.presentation.mapper

import com.ob.foodapp.feature.result.domain.model.DomainResultItem
import com.ob.foodapp.feature.result.presentation.model.UiResultItem

class UiResultMapper {

    fun DomainResultItem.fromDomainToUi(likeList: MutableList<String>) = UiResultItem(
        id = id ?: "",
        imageUrl = imageUrl ?: "",
        name = name ?: "",
        price = price ?: 0,
        rating = rating ?: 0f,
        favorite = likeList.contains(id)
    )
}