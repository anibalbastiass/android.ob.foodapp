package com.ob.foodapp.feature.profile.presentation.action

import com.ob.foodapp.feature.profile.domain.model.DomainProfile
import com.ob.foodapp.feature.result.domain.model.DomainResultItem
import com.ob.mvicore.MviAction

sealed class ProfileAction : MviAction {
    class GetProfileSuccess(val profile: DomainProfile) : ProfileAction()
    object UpdateProfileSuccess : ProfileAction()
    object GetProfileError : ProfileAction()
}