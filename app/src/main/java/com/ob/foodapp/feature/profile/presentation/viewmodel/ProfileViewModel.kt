package com.ob.foodapp.feature.profile.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ob.foodapp.feature.profile.domain.usecase.GetProfileUseCase
import com.ob.foodapp.feature.profile.domain.usecase.UpdateProfileUseCase
import com.ob.foodapp.feature.profile.presentation.action.ProfileAction
import com.ob.foodapp.feature.profile.presentation.mapper.UiProfileMapper
import com.ob.foodapp.feature.profile.presentation.model.UiProfile
import com.ob.foodapp.feature.profile.presentation.viewstate.ProfileViewState
import com.ob.mvicore.MviViewModel
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val mapper: UiProfileMapper
) : MviViewModel<ProfileViewState, ProfileAction>(
    ProfileViewState.InitialState
) {
    override fun onReduceState(viewAction: ProfileAction) = when (viewAction) {
        is ProfileAction.GetProfileSuccess -> ProfileViewState.GetProfile(
            profile = with(mapper) { viewAction.profile.fromDomainToUi() }
        )

        ProfileAction.GetProfileError -> ProfileViewState.NotFoundProfile
        ProfileAction.UpdateProfileSuccess -> ProfileViewState.UpdateProfile
    }

    fun getProfile(uid: String) {
        viewModelScope.launch {
            try {
                getProfileUseCase.execute(uid) { profile ->
                    sendAction(ProfileAction.GetProfileSuccess(profile))
                }
            } catch (e: Exception) {
                sendAction(ProfileAction.GetProfileError)
            }
        }
    }

    fun updateProfile(profile: UiProfile, userId: String) {
        viewModelScope.launch {
            try {
                updateProfileUseCase.execute(with(mapper) {
                    profile.fromUiToDomain(userId)
                }) {
                    sendAction(ProfileAction.UpdateProfileSuccess)
                }
            } catch (e: Exception) {
                sendAction(ProfileAction.GetProfileError)
            }
        }
    }
}