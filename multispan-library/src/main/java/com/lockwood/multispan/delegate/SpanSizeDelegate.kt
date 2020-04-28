package com.lockwood.multispan.delegate

import com.lockwood.multispan.delegate.base.SpanDelegate
import com.lockwood.multispan.item.SpanItem
import kotlin.reflect.KProperty

internal class SpanSizeDelegate(
    spanItem: SpanItem,
    override val onSet: () -> Unit
) : SpanDelegate<Int>(spanItem) {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return spanItem.textSize
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        spanItem.textSize = value
        onSet()
    }

}