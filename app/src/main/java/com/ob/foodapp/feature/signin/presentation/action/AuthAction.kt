package com.ob.foodapp.feature.signin.presentation.action

import com.ob.foodapp.feature.signin.domain.model.DomainUser
import com.ob.mvicore.MviAction

sealed class AuthAction : MviAction {
    class AuthSuccess(val user: DomainUser) : AuthAction()
    class SignOutSuccess(val success: Boolean) : AuthAction()
    object UserNotFound : AuthAction()
}