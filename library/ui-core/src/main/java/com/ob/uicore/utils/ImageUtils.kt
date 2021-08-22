package com.ob.uicore.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

object ImageUtils {

    fun ImageView.loadImage(
        imageUrl: String,
        @DrawableRes placeHolder: Int,
        isRounded: Boolean = false
    ) {
        if (isRounded) {
            background = null
            Glide.with(context)
                .load(imageUrl)
                .circleCrop()
                .placeholder(placeHolder)
                .into(this)
        } else {
            Glide.with(context)
                .load(imageUrl)
                .placeholder(placeHolder)
                .into(this)
        }
    }
}