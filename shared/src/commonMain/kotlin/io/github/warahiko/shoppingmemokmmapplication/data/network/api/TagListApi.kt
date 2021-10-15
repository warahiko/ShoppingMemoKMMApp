package io.github.warahiko.shoppingmemokmmapplication.data.network.api

import io.github.warahiko.shoppingmemokmmapplication.BuildKonfig
import io.github.warahiko.shoppingmemokmmapplication.data.network.baseUrl
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.TagListResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.post

class TagListApi(
    private val client: HttpClient,
) {

    suspend fun getTagList(): TagListResponse {
        return client.post(baseUrl / "databases/${BuildKonfig.TAG_DATABASE_ID}/query")
    }
}
