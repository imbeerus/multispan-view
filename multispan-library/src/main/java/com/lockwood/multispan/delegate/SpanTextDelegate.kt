package com.lockwood.multispan.delegate

import com.lockwood.multispan.item.SpanItem
import kotlin.reflect.KProperty

class SpanTextDelegate(
    spanItem: SpanItem,
    override val onSet: () -> Unit
) : SpanDelegate<String>(spanItem) {

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return spanItem.text
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        spanItem.text = value
        onSet()
    }

}