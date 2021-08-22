package com.ob.foodapp.feature.signin.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ob.foodapp.feature.signin.domain.usecase.SignInUseCase
import com.ob.foodapp.feature.signin.domain.usecase.SignOutUseCase
import com.ob.foodapp.feature.signin.presentation.action.AuthAction
import com.ob.foodapp.feature.signin.presentation.mapper.UiUserMapper
import com.ob.foodapp.feature.signin.presentation.viewstate.AuthViewState
import com.ob.mvicore.MviViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val mapper: UiUserMapper
) : MviViewModel<AuthViewState, AuthAction>(AuthViewState.InitialState) {

    override fun onReduceState(viewAction: AuthAction) = when (viewAction) {
        is AuthAction.AuthSuccess -> AuthViewState.UserFound(
            user = with(mapper) { viewAction.user.fromDomainToUi() }
        )
        AuthAction.UserNotFound -> AuthViewState.NotUserFound
        is AuthAction.SignOutSuccess -> AuthViewState.SignOutSuccess
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                signInUseCase.execute(email, password) { auth ->
                    auth.user?.let {
                        sendAction(AuthAction.AuthSuccess(auth.user))
                    } ?: run {
                        sendAction(AuthAction.UserNotFound)
                    }
                }
            } catch (e: Exception) {
                sendAction(AuthAction.UserNotFound)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                signOutUseCase
                    .execute()
                    .catch {
                        sendAction(AuthAction.UserNotFound)
                    }
                    .collect { success ->
                        sendAction(AuthAction.SignOutSuccess(success))
                    }
            } catch (e: Exception) {
                sendAction(AuthAction.UserNotFound)
            }
        }
    }
}