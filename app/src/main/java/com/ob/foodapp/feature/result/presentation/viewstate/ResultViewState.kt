package com.ob.foodapp.feature.result.presentation.viewstate

import com.ob.foodapp.feature.result.presentation.model.UiResultItem
import com.ob.mvicore.MviViewState

sealed class ResultViewState : MviViewState {
    object InitialState : ResultViewState()
    class GetItems(val list: MutableList<UiResultItem>) : ResultViewState()
    object NotFoundItems : ResultViewState()
}
