package com.ob.foodapp.feature.result.presentation.action

import com.ob.foodapp.feature.result.domain.model.DomainResultItem
import com.ob.mvicore.MviAction

sealed class ResultAction : MviAction {
    class GetItemsSuccess(val list: MutableList<DomainResultItem>) : ResultAction()
    object GetItemsError : ResultAction()
}