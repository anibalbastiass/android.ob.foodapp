package com.ob.foodapp

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ob.foodapp.feature.profile.ui.ProfileFragmentDirections
import com.ob.foodapp.feature.result.ui.ResultFragmentDirections
import com.ob.foodapp.feature.signin.presentation.model.UiUser
import com.ob.foodapp.feature.signin.ui.SignInFragmentDirections

class FoodAppNavigator {

    fun navigateToResult(view: View, user: UiUser) {
        val direction = SignInFragmentDirections.actionNavSignInToNavResult(user)
        view.findNavController().navigateUp()
        view.findNavController().navigate(direction)
    }

    fun navigateToProfile(view: View, userId: String) {
        val direction = ResultFragmentDirections.actionNavResultToNavProfile(userId)
        view.findNavController().navigate(direction)
    }

    fun navigateToSignIn(view: View) {
        val direction = ProfileFragmentDirections.actionNavProfileToNavSignIn()
        view.findNavController().navigate(direction)
    }

    fun launchAppWithSignIn(navController: NavController) {
        val direction = AppNavigationGraphDirections.actionNavSignIn()
        navController.navigate(direction)
    }

    fun launchAppWithResult(navController: NavController, user: UiUser) {
        val direction = AppNavigationGraphDirections.actionNavResult(user)
        navController.navigateUp()
        navController.navigate(direction)
    }
}
