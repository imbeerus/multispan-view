package com.lockwood.multispan.extensions

import android.content.res.TypedArray
import androidx.annotation.ColorInt

fun TypedArray.getStringOrEmpty(index: Int): String {
    return getString(index) ?: ""
}

fun TypedArray.getTextSizeOrDefault(index: Int, default: Int): Int {
    return getDimensionPixelSize(index, default)
}

fun TypedArray.getTextColorOrDefault(index: Int, @ColorInt default: Int): Int {
    return getColor(index, default)
}