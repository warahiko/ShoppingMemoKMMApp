package io.github.warahiko.shoppingmemokmmapplication.data.mapper

import io.github.warahiko.shoppingmemokmmapplication.data.network.model.RichText
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.Text

fun String.toRichTextList(): List<RichText> {
    return listOf(RichText(Text(this)))
}
