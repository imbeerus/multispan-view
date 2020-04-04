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

package com.lockwood.multispan.roboto.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.lockwood.multispan.MultiSpanView
import com.lockwood.multispan.property.SpanProperty
import com.lockwood.multispan.roboto.R
import com.lockwood.multispan.roboto.item.RobotoSpanItem
import com.lockwood.multispan.roboto.spannable.RobotoSpannable
import kotlin.reflect.KProperty

abstract class RobotoMultiSpanView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : MultiSpanView<RobotoSpanItem>(context, attrs, defStyleAttr), RobotoSpannable {

    companion object {

        private const val REGULAR_ROBOTO_FONT = 0
    }

    private val robotoFontFamilies = resources.getStringArray(R.array.roboto_font_families)
    private val robotoStyles = resources.getIntArray(R.array.roboto_styles)

    override fun initSpan() = RobotoSpanItem(
        textSize = textSize.toInt(),
        textColor = currentTextColor
    )

    //region TypedArray extensions
    protected fun TypedArray.getRobotoFontFamily(index: Int): String {
        val position = getInt(index, REGULAR_ROBOTO_FONT)
        return try {
            robotoFontFamilies[position]
        } catch (e: Exception) {
            robotoFontFamilies[REGULAR_ROBOTO_FONT]
        }
    }

    protected fun TypedArray.getRobotoFontStyle(index: Int): Int {
        val position = getInt(index, REGULAR_ROBOTO_FONT)
        return try {
            robotoStyles[position]
        } catch (e: Exception) {
            robotoStyles[REGULAR_ROBOTO_FONT]
        }
    }
    // endregion

    //region Roboto Span property functions
    protected inline fun fontProperty(position: () -> Int) =
        SpanFontDelegate(position())

    protected inline fun styleProperty(position: () -> Int) =
        SpanStyleDelegate(position())
    // endregion

    //region Roboto span property delegates
    protected inner class SpanFontDelegate(
        position: Int
    ) : SpanProperty<String>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return (spanItems[position] as RobotoSpanItem).fontFamily
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            (spanItems[position] as RobotoSpanItem).fontFamily = value
            updateSpanStyles()
        }
    }

    protected inner class SpanStyleDelegate(
        position: Int
    ) : SpanProperty<Int>(position) {

        override fun getValue(thisRef: Any, property: KProperty<*>): Int {
            return (spanItems[position] as RobotoSpanItem).style
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
            (spanItems[position] as RobotoSpanItem).style = value
            updateSpanStyles()
        }
    }
    // endregion

}

