package io.github.warahiko.shoppingmemokmmapplication.data.network.api

import io.github.warahiko.shoppingmemokmmapplication.BuildKonfig
import io.github.warahiko.shoppingmemokmmapplication.data.network.baseUrl
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.GetShoppingListRequest
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.ShoppingItemListResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShoppingItemListApi : KoinComponent {

    private val client: HttpClient by inject()

    suspend fun getShoppingList(request: GetShoppingListRequest): ShoppingItemListResponse {
        return client.post(baseUrl / "databases/${BuildKonfig.DATABASE_ID}/query") {
            body = request
        }
    }
}