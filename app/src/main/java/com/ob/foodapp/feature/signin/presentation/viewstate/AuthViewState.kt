package com.ob.foodapp.feature.signin.presentation.viewstate

import com.ob.foodapp.feature.signin.presentation.model.UiUser
import com.ob.mvicore.MviViewState

sealed class AuthViewState : MviViewState {
    object InitialState : AuthViewState()
    class UserFound(val user: UiUser) : AuthViewState()
    object SignOutSuccess : AuthViewState()
    object NotUserFound : AuthViewState()
}
