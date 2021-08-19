package com.ob.foodapp

import android.view.View
import androidx.navigation.findNavController
import com.ob.foodapp.feature.result.ui.ResultFragmentDirections
import com.ob.foodapp.feature.signin.ui.SignInFragmentDirections

class FoodAppNavigator {

    fun navigateToResult(view: View, email: String) {
        val direction = SignInFragmentDirections.actionNavSignInToNavResult(email)
        view.findNavController().navigate(direction)
    }

    fun navigateToProfile(view: View, email: String) {
        val direction = ResultFragmentDirections.actionNavResultToNavProfile(email)
        view.findNavController().navigate(direction)
    }
}
