package com.lockwood.multispandemo

import androidx.databinding.BindingAdapter
import com.lockwood.multispan.roboto.RobotoFourSpanView
import com.lockwood.multispan.roboto.RobotoThreeSpanView
import com.lockwood.multispan.roboto.RobotoTwoSpanView

@BindingAdapter(
    value = [
        "firstText",
        "secondText"
    ]
)
fun RobotoTwoSpanView.setText(
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
fun RobotoThreeSpanView.setText(
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
fun RobotoFourSpanView.setText(
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