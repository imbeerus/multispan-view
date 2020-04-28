package com.lockwood.multispan.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.ColorInt
import com.lockwood.multispan.R
import com.lockwood.multispan.spannable.FourSpan
import com.lockwood.multispan.spannable.SingleSpan
import com.lockwood.multispan.spannable.ThreeSpan
import com.lockwood.multispan.spannable.TwoSpan

inline fun fetchAttrs(
    attrs: IntArray,
    context: Context,
    set: AttributeSet?,
    fetch: TypedArray.() -> Unit = {}
) {
    context.theme.obtainStyledAttributes(
        set,
        attrs,
        0,
        0
    ).apply {
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
    fetchAttrs(R.styleable.SingleSpanView, context, attrs) {
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

    fetchAttrs(R.styleable.TwoSpanView, context, attrs) {
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

    fetchAttrs(R.styleable.ThreeSpanView, context, attrs) {
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

    fetchAttrs(R.styleable.FourSpanView, context, attrs) {
        fourthText = getStringOrEmpty(R.styleable.FourSpanView_fourthText)
        fourthTextSize = getTextSizeOrDefault(R.styleable.FourSpanView_fourthTextSize, textSize)
        fourthTextColor = getTextColorOrDefault(R.styleable.FourSpanView_fourthTextColor, textColor)
        fourthSeparator = getStringOrEmpty(R.styleable.FourSpanView_fourthSeparator)
    }
}