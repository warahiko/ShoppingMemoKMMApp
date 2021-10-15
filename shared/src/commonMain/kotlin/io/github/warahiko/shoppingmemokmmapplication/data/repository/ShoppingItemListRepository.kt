package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.ShoppingItemListApi
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.GetShoppingListRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingItemListRepository constructor(
    private val shoppingItemListApi: ShoppingItemListApi,
) {

    suspend fun fetchShoppingList(): List<ShoppingItem> {
        val request = GetShoppingListRequest()
        val shoppingList = withContext(Dispatchers.Default) {
            shoppingItemListApi.getShoppingList(request)
        }
        return shoppingList.results.map {
            it.toShoppingItem()
        }
    }
}
