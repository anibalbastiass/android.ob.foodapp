package com.ob.foodapp.feature.signin.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ob.foodapp.feature.signin.domain.usecase.SignInUseCase
import com.ob.foodapp.feature.signin.presentation.action.SignInAction
import com.ob.foodapp.feature.signin.presentation.mapper.UiUserMapper
import com.ob.foodapp.feature.signin.presentation.viewstate.SignInViewState
import com.ob.mvicore.MviViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val mapper: UiUserMapper
): MviViewModel<SignInViewState, SignInAction>(SignInViewState.InitialState) {

    override fun onReduceState(viewAction: SignInAction) = when(viewAction) {
        is SignInAction.SignInSuccess -> SignInViewState.UserFound(
            user = with(mapper) { viewAction.user.fromDomainToUi() }
        )
        SignInAction.UserNotFound -> SignInViewState.NotUserFound
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                signInUseCase.execute(email, password) { auth ->
                    auth.user?.let {
                        sendAction(SignInAction.SignInSuccess(auth.user))
                    } ?: run {
                        sendAction(SignInAction.UserNotFound)
                    }
                }
            } catch (e: Exception) {
                sendAction(SignInAction.UserNotFound)
            }
        }
    }
}