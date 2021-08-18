package com.ob.uicore.atoms

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ob.uicore.utils.FontHelper

class TitleTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        paintFlags = paintFlags or Paint.SUBPIXEL_TEXT_FLAG
    }

    fun initTitleText(
        context: Context,
        fullText: String,
        boldText: String
    ) {
        FontHelper.setSpannableText(
            context = context,
            textView = this@TitleTextView,
            fullText = fullText,
            boldText = boldText
        )
    }
}