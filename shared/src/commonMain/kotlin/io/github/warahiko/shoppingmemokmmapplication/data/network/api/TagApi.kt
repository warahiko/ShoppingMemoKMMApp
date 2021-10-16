package io.github.warahiko.shoppingmemokmmapplication.data.network.api

import io.github.warahiko.shoppingmemokmmapplication.BuildKonfig
import io.github.warahiko.shoppingmemokmmapplication.data.network.baseUrl
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.AddTagRequest
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.GetTagsResponse
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.TagPage
import io.ktor.client.HttpClient
import io.ktor.client.request.post

class TagApi(
    private val client: HttpClient,
) {

    suspend fun getTags(): GetTagsResponse {
        return client.post(baseUrl / "databases/${BuildKonfig.TAG_DATABASE_ID}/query")
    }

    suspend fun addTag(request: AddTagRequest): TagPage {
        return client.post(baseUrl / "pages") {
            body = request
        }
    }
}
