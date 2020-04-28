package com.lockwood.multispan.delegate

import com.lockwood.multispan.delegate.base.SpanDelegate
import com.lockwood.multispan.item.SpanItem
import kotlin.reflect.KProperty

internal class SpanSeparatorDelegate(
    spanItem: SpanItem,
    override val onSet: () -> Unit
) : SpanDelegate<String>(spanItem) {

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return spanItem.separator
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        spanItem.separator = value
        onSet()
    }

}