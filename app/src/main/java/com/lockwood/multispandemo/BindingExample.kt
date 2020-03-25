package com.lockwood.multispandemo

import androidx.databinding.BindingAdapter
import com.lockwood.multispan.spannable.FourSpan
import com.lockwood.multispan.spannable.ThreeSpan
import com.lockwood.multispan.spannable.TwoSpan

@BindingAdapter(
    value = [
        "firstText",
        "secondText"
    ]
)
fun TwoSpan<*>.setGravityDrawables(
    firstStr: String,
    secondStr: String
) {
    firstText = firstStr
    secondText = secondStr
}

@BindingAdapter(
    value = [
        "firstText",
        "secondText",
        "thirdText"
    ]
)
fun ThreeSpan<*>.setGravityDrawables(
    firstStr: String,
    secondStr: String,
    thirdStr: String
) {
    firstText = firstStr
    secondText = secondStr
    thirdText = thirdStr
}

@BindingAdapter(
    value = [
        "firstText",
        "secondText",
        "thirdText",
        "fourthText"
    ]
)
fun FourSpan<*>.setGravityDrawables(
    firstStr: String,
    secondStr: String,
    thirdStr: String,
    fourthStr: String
) {
    firstText = firstStr
    secondText = secondStr
    thirdText = thirdStr
    fourthText = fourthStr
}