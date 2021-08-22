package com.ob.foodapp.feature.profile.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UiProfile(
    val id: String,
    var name: String,
    val email: String,
    var city: String,
    var bio: String,
    val avatarUrl: String,
    val likes: MutableList<String>
) : Parcelable