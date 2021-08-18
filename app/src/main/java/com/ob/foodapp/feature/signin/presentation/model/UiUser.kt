package com.ob.foodapp.feature.signin.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UiUser(
    val name: String,
    val email: String
) : Parcelable