/*
 * Copyright (C) 2020  Ivan Zinovyev (https://github.com/lndmflngs)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lockwood.multispan

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.util.AttributeSet
import com.lockwood.compound.CompoundTextView
import com.lockwood.multispan.item.SpanItem
import com.lockwood.multispan.property.SpanProperty
import com.lockwood.multispan.spannable.*
import kotlin.reflect.KProperty

abstract class MultiSpanView<T : SpanItem> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : CompoundTextView(context, attrs, defStyleAttr), Spannable<T> {

    companion object {

        private val TAG = MultiSpanView::class.java.simpleName

        const val DEF_SPANS_COUNT = 0
        const val MAX_SPANS_COUNT = 4
        const val DEF_ORIENTATION = Orientation.HORIZONTAL
    }

    var orientation = DEF_ORIENTATION
        set(value) {
            field = value
            updateSpanStyles()
        }

    protected open val spansCount = DEF_SPANS_COUNT

    protected open val defaultTextColor = Color.BLACK

    protected var spanItems = Array(MAX_SPANS_COUNT) { initSpan() }

    private val spannableBuilder = SpannableStringBuilder()

    init {
        fetchAttrs(R.styleable.MultiSpanView, context, attrs) {
            orientation = getInt(
                R.styleable.MultiSpanView_orientation,
                DEF_ORIENTATION
            )
        }
        updateSpanStyles()
    }

    protected fun updateSpanStyles() {
        spannableBuilder.clear()
        for (position in 0 until spansCount) {
            val item = spanItems[position]
            spannableBuilder.applySpanStyle(position, item)
        }
        val result = SpannableString(spannableBuilder.trimEnd())
        val spannableString = if (result.isNotEmpty()) {
            setSpanOnResult(result)
        } else {
            result
        }
        setText(spannableString, BufferType.SPANNABLE)
    }

    protected open fun setSpanOnResult(resultSpans: SpannableString): CharSequence {
        return resultSpans
    }

    //region TypedArray extensions
    protected fun TypedArray.getStringOrEmpty(index: Int): String {
        return getString(index) ?: ""
    }

    protected fun TypedArray.getTextSizeOrDefault(index: Int): Int {
        return getDimensionPixelSize(index, textSize.toInt())
    }

    protected fun TypedArray.getTextColorOrDefault(index: Int): Int {
        return getColor(index, currentTextColor)
    }
    //endregion

    //region MultiSpan fetchAttrs
    protected inline fun fetchAttrs(
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

    protected fun SingleSpan<*>.fetchSingleSpanAttrs(
        context: Context,
        attrs: AttributeSet?
    ) {
        fetchAttrs(R.styleable.SingleSpanView, context, attrs) {
            firstText = getStringOrEmpty(R.styleable.SingleSpanView_firstText)
            firstTextSize = getTextSizeOrDefault(R.styleable.SingleSpanView_firstTextSize)
            firstTextColor = getTextColorOrDefault(R.styleable.SingleSpanView_firstTextColor)
            firstSeparator = getStringOrEmpty(R.styleable.SingleSpanView_firstSeparator)
        }
    }

    protected fun TwoSpan<*>.fetchTwoSpanAttrs(
        context: Context,
        attrs: AttributeSet?
    ) {
        fetchSingleSpanAttrs(context, attrs)
        fetchAttrs(R.styleable.TwoSpanView, context, attrs) {
            secondText = getStringOrEmpty(R.styleable.TwoSpanView_secondText)
            secondTextSize = getTextSizeOrDefault(R.styleable.TwoSpanView_secondTextSize)
            secondTextColor = getTextColorOrDefault(R.styleable.TwoSpanView_secondTextColor)
            secondSeparator = getStringOrEmpty(R.styleable.TwoSpanView_secondSeparator)
        }
    }

    protected fun ThreeSpan<*>.fetchThreeSpanAttrs(
        context: Context,
        attrs: AttributeSet?
    ) {
        fetchTwoSpanAttrs(context, attrs)
        fetchAttrs(R.styleable.ThreeSpanView, context, attrs) {
            thirdText = getStringOrEmpty(R.styleable.ThreeSpanView_thirdText)
            thirdTextSize = getTextSizeOrDefault(R.styleable.ThreeSpanView_thirdTextSize)
            thirdTextColor = getTextColorOrDefault(R.styleable.ThreeSpanView_thirdTextColor)
            thirdSeparator = getStringOrEmpty(R.styleable.ThreeSpanView_thirdSeparator)
        }
    }

    protected fun FourSpan<*>.fetchFourSpanAttrs(
        context: Context,
        attrs: AttributeSet?
    ) {
        fetchThreeSpanAttrs(context, attrs)
        fetchAttrs(R.styleable.FourSpanView, context, attrs) {
            fourthText = getStringOrEmpty(R.styleable.FourSpanView_fourthText)
            fourthTextSize = getTextSizeOrDefault(R.styleable.FourSpanView_fourthTextSize)
            fourthTextColor = getTextColorOrDefault(R.styleable.FourSpanView_fourthTextColor)
            fourthSeparator = getStringOrEmpty(R.styleable.FourSpanView_fourthSeparator)
        }
    }
    //endregion

    //region Span property functions
    protected inline fun textProperty(position: () -> Int) =
        SpanTextProperty(position())

    protected inline fun textSizeProperty(position: () -> Int) =
        SpanSizeDelegate(position())

    protected inline fun textColorProperty(position: () -> Int) =
        SpanColorDelegate(position())

    protected inline fun textSeparatorProperty(position: () -> Int) =
        SpanSeparatorDelegate(position())
    //endregion

    //region Span property delegates
    protected inner class SpanTextProperty(position: Int) : SpanProperty<String>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return spanItems[position].text
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            spanItems[position].text = value
            updateSpanStyles()
        }
    }

    protected inner class SpanSizeDelegate(position: Int) : SpanProperty<Int>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): Int {
            return spanItems[position].textSize
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
            spanItems[position].textSize = value
            updateSpanStyles()
        }
    }

    protected inner class SpanColorDelegate(position: Int) : SpanProperty<Int>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): Int {
            return spanItems[position].textColor
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
            spanItems[position].textColor = value
            updateSpanStyles()
        }
    }

    protected inner class SpanSeparatorDelegate(position: Int) : SpanProperty<String>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return spanItems[position].separator
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            spanItems[position].separator = value
            updateSpanStyles()
        }
    }
    //endregion

    private fun SpannableStringBuilder.applySpanStyle(position: Int, spanItem: SpanItem) {
        val span = spanItem.buildSpan(position)
        val text = spanItem.text.plus("${spanItem.separator} ")
        val start = if (orientation == Orientation.VERTICAL) {
            appendln(text)
            length - text.length - 1
        } else {
            append(text)
            length - text.length
        }
        val end = length
        setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    object Orientation {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
    }

}

