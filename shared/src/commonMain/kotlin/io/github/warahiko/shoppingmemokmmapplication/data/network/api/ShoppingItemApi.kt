package io.github.warahiko.shoppingmemokmmapplication.data.network.api

import io.github.warahiko.shoppingmemokmmapplication.BuildKonfig
import io.github.warahiko.shoppingmemokmmapplication.data.network.baseUrl
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.GetShoppingItemsRequest
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.ShoppingItemsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.post

class ShoppingItemApi(
    private val client: HttpClient,
) {

    suspend fun getShoppingItems(request: GetShoppingItemsRequest): ShoppingItemsResponse {
        return client.post(baseUrl / "databases/${BuildKonfig.DATABASE_ID}/query") {
            body = request
        }
    }
}
