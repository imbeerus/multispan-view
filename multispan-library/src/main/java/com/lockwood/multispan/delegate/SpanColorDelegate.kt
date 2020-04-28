package com.lockwood.multispan.delegate

import com.lockwood.multispan.item.SpanItem
import kotlin.reflect.KProperty

class SpanColorDelegate(
    spanItem: SpanItem,
    override val onSet: () -> Unit
) : SpanDelegate<Int>(spanItem) {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return spanItem.textColor
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        spanItem.textColor = value
        onSet()
    }

}