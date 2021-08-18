package com.ob.foodapp.feature.signin.presentation.viewstate

import com.ob.foodapp.feature.signin.presentation.model.UiUser
import com.ob.mvicore.MviViewState

sealed class SignInViewState : MviViewState {
    object InitialState : SignInViewState()
    class UserFound(val user: UiUser) : SignInViewState()
    object NotUserFound : SignInViewState()
}
