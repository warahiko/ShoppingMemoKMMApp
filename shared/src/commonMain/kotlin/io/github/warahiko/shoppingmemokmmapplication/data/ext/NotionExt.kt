package io.github.warahiko.shoppingmemokmmapplication.data.ext

import io.github.warahiko.shoppingmemokmmapplication.data.network.model.RichText

fun List<RichText>.concatText(): String {
    return fold("") { acc, richText ->
        acc + richText.text.content
    }
}
