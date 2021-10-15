package io.github.warahiko.shoppingmemokmmapplication.data.network

import io.github.warahiko.shoppingmemokmmapplication.BuildKonfig
import kotlin.jvm.JvmInline
import kotlin.native.concurrent.ThreadLocal

@JvmInline
internal value class BaseUrl(private val value: String) {

    operator fun div(relativePath: String): String {
        return "$value/$relativePath"
    }
}

@ThreadLocal
internal val baseUrl = BaseUrl(BuildKonfig.NOTION_BASE_URL.trimEnd('/'))
