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

package com.lockwood.multispan.font

import android.content.Context
import android.util.AttributeSet
import com.lockwood.multispan.font.base.FontMultiSpanView
import com.lockwood.multispan.font.item.FontSpanItem
import com.lockwood.multispan.font.spannable.FontTwoSpan
import com.lockwood.multispan.spannable.TwoSpan.Companion.ITEM_FIRST
import com.lockwood.multispan.spannable.TwoSpan.Companion.ITEM_SECOND
import com.lockwood.multispan.spannable.TwoSpan.Companion.TWO_ITEMS_COUNT

open class FontTwoSpanView<T : FontSpanItem> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FontMultiSpanView<T>(context, attrs), FontTwoSpan<T> {

    override var spansCount = TWO_ITEMS_COUNT

    override var firstText by textProperty { ITEM_FIRST }
    override var firstTextSize by sizeProperty { ITEM_FIRST }
    override var firstTextColor by textColorProperty { ITEM_FIRST }
    override var firstSeparator by textSeparatorProperty { ITEM_FIRST }

    override var firstFont by fontProperty { ITEM_FIRST }

    override var secondText by textProperty { ITEM_SECOND }
    override var secondTextSize by sizeProperty { ITEM_SECOND }
    override var secondTextColor by textColorProperty { ITEM_SECOND }
    override var secondSeparator by textSeparatorProperty { ITEM_SECOND }

    override var secondFont by fontProperty { ITEM_SECOND }

    init {
        fetchAttrs(R.styleable.MultiSpanView, context, attrs) {
            firstText = getStringOrEmpty(R.styleable.MultiSpanView_firstText)
            firstTextSize = getTextSizeOrCurrent(R.styleable.MultiSpanView_firstTextSize)
            firstTextColor = getTextColorOrCurrent(R.styleable.MultiSpanView_firstTextColor)
            firstSeparator = getStringOrEmpty(R.styleable.MultiSpanView_firstSeparator)

            secondText = getStringOrEmpty(R.styleable.MultiSpanView_secondText)
            secondTextSize = getTextSizeOrCurrent(R.styleable.MultiSpanView_secondTextSize)
            secondTextColor = getTextColorOrCurrent(R.styleable.MultiSpanView_secondTextColor)
            secondSeparator = getStringOrEmpty(R.styleable.MultiSpanView_secondSeparator)
        }
        fetchAttrs(R.styleable.FontTwoSpanView, context, attrs) {
            firstFont = getFontOrDefault(R.styleable.FontTwoSpanView_firstFont)

            secondFont = getFontOrDefault(R.styleable.FontTwoSpanView_secondFont)
        }
    }

}