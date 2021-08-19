package com.ob.uicore.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

object ImageUtils {

    fun ImageView.loadImage(
        imageUrl: String,
        @DrawableRes placeHolder: Int
    ) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeHolder)
            .into(this)
    }
}