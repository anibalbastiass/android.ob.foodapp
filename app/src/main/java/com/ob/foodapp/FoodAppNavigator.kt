package com.ob.foodapp

import android.view.View
import androidx.navigation.findNavController
import com.ob.foodapp.feature.profile.presentation.model.UiProfile
import com.ob.foodapp.feature.result.ui.ResultFragmentDirections
import com.ob.foodapp.feature.signin.presentation.model.UiUser
import com.ob.foodapp.feature.signin.ui.SignInFragmentDirections

class FoodAppNavigator {

    fun navigateToResult(view: View, user: UiUser) {
        val direction = SignInFragmentDirections.actionNavSignInToNavResult(user)
        view.findNavController().navigate(direction)
    }

    fun navigateToProfile(view: View, userId: String) {
        val direction = ResultFragmentDirections.actionNavResultToNavProfile(userId)
        view.findNavController().navigate(direction)
    }
}
