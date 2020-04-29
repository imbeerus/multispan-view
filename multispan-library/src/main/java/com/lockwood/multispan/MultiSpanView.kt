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
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.util.AttributeSet
import com.lockwood.compound.CompoundTextView
import com.lockwood.multispan.delegate.SpanColorDelegate
import com.lockwood.multispan.delegate.SpanSeparatorDelegate
import com.lockwood.multispan.delegate.SpanSizeDelegate
import com.lockwood.multispan.delegate.SpanTextDelegate
import com.lockwood.multispan.extensions.fetchAttrs
import com.lockwood.multispan.item.SpanItem
import com.lockwood.multispan.spannable.Spannable

abstract class MultiSpanView<T : SpanItem> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : CompoundTextView(context, attrs, defStyleAttr), Spannable<T> {

    companion object {

        private val TAG = MultiSpanView::class.java.simpleName

        const val MAX_SPANS_COUNT = 4
        const val DEF_ORIENTATION = Orientation.HORIZONTAL
    }

    @PublishedApi
    internal fun `access$updateSpanStyles`() = updateSpanStyles()

    @PublishedApi
    internal val `access$spanItems`: Array<SpanItem>
        get() = spanItems

    //region Fields
    abstract val spansCount: Int

    var orientation = DEF_ORIENTATION
        set(value) {
            field = value
            updateSpanStyles()
        }

    private val spanItems = Array(MAX_SPANS_COUNT) { initSpan() }

    protected open val defaultTextSize
        get() = textSize.toInt()

    protected open val defaultTextColor
        get() = currentTextColor

    private val spannableBuilder = SpannableStringBuilder()
    //endregion

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

     inline fun findSpan(position: () -> Int): SpanItem {
        return `access$spanItems`[position()]
    }

    //region Span property functions
    inline fun textProperty(position: () -> Int): SpanTextDelegate {
        val item = findSpan(position)
        return SpanTextDelegate(item) { `access$updateSpanStyles`() }
    }

    inline fun textSizeProperty(position: () -> Int): SpanSizeDelegate {
        val item = findSpan(position)
        return SpanSizeDelegate(item) { `access$updateSpanStyles`() }
    }

    inline fun textColorProperty(position: () -> Int): SpanColorDelegate {
        val item = findSpan(position)
        return SpanColorDelegate(item) { `access$updateSpanStyles`() }
    }

    inline fun textSeparatorProperty(position: () -> Int): SpanSeparatorDelegate {
        val item = findSpan(position)
        return SpanSeparatorDelegate(item) { `access$updateSpanStyles`() }
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

