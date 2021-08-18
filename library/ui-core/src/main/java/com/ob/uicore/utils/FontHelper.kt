package com.ob.uicore.utils

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.ob.uicore.R

object FontHelper {
    fun setSpannableText(
        context: Context,
        textView: TextView,
        fullText: String,
        boldText: String
    ) {
        val fontRegular = ResourcesCompat.getFont(context, R.font.poppins_regular)
        val fontBold = ResourcesCompat.getFont(context, R.font.poppins_bold)

        val stringBuilder = SpannableStringBuilder(fullText)
        stringBuilder.setSpan(
            FoodAppTypefaceSpan("", fontRegular),
            0,
            fullText.indexOf(boldText),
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )
        stringBuilder.setSpan(
            FoodAppTypefaceSpan("", fontBold),
            fullText.indexOf(boldText),
            fullText.length,
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )

        textView.text = stringBuilder
    }
}