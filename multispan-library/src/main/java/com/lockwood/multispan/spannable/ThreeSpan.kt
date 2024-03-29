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

package com.lockwood.multispan.spannable

import com.lockwood.multispan.item.SpanItem

interface ThreeSpan<T : SpanItem> : TwoSpan<T> {

    companion object {

        const val THREE_ITEMS_COUNT = 3

        const val ITEM_THIRD = 2
    }

    var thirdText: String
    var thirdTextSize: Int
    var thirdTextColor: Int
    var thirdSeparator: String

}