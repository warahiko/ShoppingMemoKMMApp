package io.github.warahiko.shoppingmemokmmapplication.data.network

import io.github.warahiko.shoppingmemokmmapplication.BuildKonfig
import kotlin.jvm.JvmInline

@JvmInline
internal value class BaseUrl(private val value: String) {

    operator fun div(relativePath: String): String {
        return "$value/$relativePath"
    }
}

internal val baseUrl = BaseUrl(BuildKonfig.NOTION_BASE_URL.trimEnd('/'))
