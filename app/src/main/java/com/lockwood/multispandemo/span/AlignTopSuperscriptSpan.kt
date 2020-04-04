package com.lockwood.multispandemo.span

import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class AlignTopSuperscriptSpan : MetricAffectingSpan() {

    override fun updateDrawState(tp: TextPaint) {
        tp.baselineShift += (tp.ascent() / DEFAULT_SHIFT_SCALE).toInt()
    }

    override fun updateMeasureState(tp: TextPaint) {
        tp.baselineShift += (tp.ascent() / DEFAULT_SHIFT_SCALE).toInt()
    }

    companion object {

        private const val DEFAULT_SHIFT_SCALE = 1.25
    }

}
