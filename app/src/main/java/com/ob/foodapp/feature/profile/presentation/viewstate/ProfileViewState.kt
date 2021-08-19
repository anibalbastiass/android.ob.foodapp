package com.ob.foodapp.feature.profile.presentation.viewstate

import com.ob.foodapp.feature.profile.presentation.model.UiProfile
import com.ob.foodapp.feature.result.presentation.model.UiResultItem
import com.ob.mvicore.MviViewState

sealed class ProfileViewState : MviViewState {
    object InitialState : ProfileViewState()
    class GetProfile(val profile: UiProfile) : ProfileViewState()
    object NotFoundProfile : ProfileViewState()
}
