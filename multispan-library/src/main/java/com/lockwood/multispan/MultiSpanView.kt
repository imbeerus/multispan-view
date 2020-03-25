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
import com.lockwood.multispan.spannable.Spannable
import kotlin.reflect.KProperty

abstract class MultiSpanView<T : SpanItem> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : CompoundTextView(context, attrs, defStyleAttr), Spannable<T> {

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

    protected open fun setSpanOnResult(resultSpans: SpannableString): CharSequence =
        resultSpans

    protected fun TypedArray.getStringOrEmpty(index: Int) =
        getString(index) ?: EMPTY_STRING

    protected fun TypedArray.getTextSizeOrCurrent(index: Int) =
        getDimensionPixelSize(index, textSize.toInt())

    protected fun TypedArray.getTextColorOrDefault(index: Int) =
        getColor(index, defaultTextColor)

    protected inline fun textProperty(position: () -> Int) =
        SpanTextProperty(position())

    protected inline fun textSizeProperty(position: () -> Int) =
        SpanSizeProperty(position())

    protected inline fun textColorProperty(position: () -> Int) =
        SpanColorProperty(position())

    protected inline fun textSeparatorProperty(position: () -> Int) =
        SpanSeparatorProperty(position())

    protected inner class SpanTextProperty(position: Int) : SpanProperty<String>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return spanItems[position].text
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            spanItems[position].text = value
            updateSpanStyles()
        }
    }

    protected inner class SpanSizeProperty(position: Int) : SpanProperty<Int>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): Int {
            return spanItems[position].textSize
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
            spanItems[position].textSize = value
            updateSpanStyles()
        }
    }

    protected inner class SpanColorProperty(position: Int) : SpanProperty<Int>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): Int {
            return spanItems[position].textColor
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
            spanItems[position].textColor = value
            updateSpanStyles()
        }
    }

    protected inner class SpanSeparatorProperty(position: Int) : SpanProperty<String>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return spanItems[position].separator
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            spanItems[position].separator = value
            updateSpanStyles()
        }
    }

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

    companion object {

        private val TAG = MultiSpanView::class.java.simpleName

        const val EMPTY_STRING = ""
        const val DEF_SPANS_COUNT = 0
        const val MAX_SPANS_COUNT = 4
        const val DEF_ORIENTATION = Orientation.HORIZONTAL
    }

}

