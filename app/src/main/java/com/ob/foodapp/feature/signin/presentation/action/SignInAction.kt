package com.ob.foodapp.feature.signin.presentation.action

import com.ob.foodapp.feature.signin.domain.model.DomainUser
import com.ob.mvicore.MviAction

sealed class SignInAction : MviAction {
    class SignInSuccess(val user: DomainUser) : SignInAction()
    object UserNotFound : SignInAction()
}