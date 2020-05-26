package com.lockwood.multispan.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import com.lockwood.multispan.R
import com.lockwood.multispan.spannable.FourSpan
import com.lockwood.multispan.spannable.SingleSpan
import com.lockwood.multispan.spannable.ThreeSpan
import com.lockwood.multispan.spannable.TwoSpan

internal inline fun fetchAttrs(
    context: Context,
    attrs: IntArray,
    set: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    fetch: TypedArray .() -> Unit = {}
) {
    val typedArray = context.theme.obtainStyledAttributes(
        set,
        attrs,
        defStyleAttr,
        defStyleRes
    )
    with(typedArray) {
        try {
            fetch()
        } finally {
            recycle()
        }
    }
}

fun SingleSpan<*>.fetchSingleSpanAttrs(
    context: Context,
    attrs: AttributeSet?,
    textSize: Int,
    @ColorInt textColor: Int
) {
    fetchAttrs(context, R.styleable.SingleSpanView, attrs) {
        firstText = getStringOrEmpty(R.styleable.SingleSpanView_firstText)
        firstTextSize = getTextSizeOrDefault(R.styleable.SingleSpanView_firstTextSize, textSize)
        firstTextColor = getTextColorOrDefault(R.styleable.SingleSpanView_firstTextColor, textColor)
        firstSeparator = getStringOrEmpty(R.styleable.SingleSpanView_firstSeparator)
    }
}

fun TwoSpan<*>.fetchTwoSpanAttrs(
    context: Context,
    attrs: AttributeSet?,
    textSize: Int,
    @ColorInt textColor: Int
) {
    fetchSingleSpanAttrs(context, attrs, textSize, textColor)

    fetchAttrs(context, R.styleable.TwoSpanView, attrs) {
        secondText = getStringOrEmpty(R.styleable.TwoSpanView_secondText)
        secondTextSize = getTextSizeOrDefault(R.styleable.TwoSpanView_secondTextSize, textSize)
        secondTextColor = getTextColorOrDefault(R.styleable.TwoSpanView_secondTextColor, textColor)
        secondSeparator = getStringOrEmpty(R.styleable.TwoSpanView_secondSeparator)
    }
}

fun ThreeSpan<*>.fetchThreeSpanAttrs(
    context: Context,
    attrs: AttributeSet?,
    textSize: Int,
    @ColorInt textColor: Int
) {
    fetchTwoSpanAttrs(context, attrs, textSize, textColor)

    fetchAttrs(context, R.styleable.ThreeSpanView, attrs) {
        thirdText = getStringOrEmpty(R.styleable.ThreeSpanView_thirdText)
        thirdTextSize = getTextSizeOrDefault(R.styleable.ThreeSpanView_thirdTextSize, textSize)
        thirdTextColor = getTextColorOrDefault(R.styleable.ThreeSpanView_thirdTextColor, textColor)
        thirdSeparator = getStringOrEmpty(R.styleable.ThreeSpanView_thirdSeparator)
    }
}

fun FourSpan<*>.fetchFourSpanAttrs(
    context: Context,
    attrs: AttributeSet?,
    textSize: Int,
    @ColorInt textColor: Int
) {
    fetchThreeSpanAttrs(context, attrs, textSize, textColor)

    fetchAttrs(context, R.styleable.FourSpanView, attrs) {
        fourthText = getStringOrEmpty(R.styleable.FourSpanView_fourthText)
        fourthTextSize = getTextSizeOrDefault(R.styleable.FourSpanView_fourthTextSize, textSize)
        fourthTextColor = getTextColorOrDefault(R.styleable.FourSpanView_fourthTextColor, textColor)
        fourthSeparator = getStringOrEmpty(R.styleable.FourSpanView_fourthSeparator)
    }
}