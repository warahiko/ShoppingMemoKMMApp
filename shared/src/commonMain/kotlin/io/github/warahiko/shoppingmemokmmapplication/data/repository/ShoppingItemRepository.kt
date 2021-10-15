package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toShoppingItemWithTag
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.ShoppingItemApi
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.GetShoppingItemsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class ShoppingItemRepository(
    private val shoppingItemApi: ShoppingItemApi,
    private val tagRepository: TagRepository,
) {

    private val _shoppingItems: MutableStateFlow<List<ShoppingItem>?> = MutableStateFlow(null)
    val shoppingItems: StateFlow<List<ShoppingItem>?> get() = _shoppingItems

    suspend fun fetchShoppingItems(): List<ShoppingItem> {
        val request = GetShoppingItemsRequest()
        val (shoppingItems, tags) = withContext(Dispatchers.Default) {
            val shoppingItemsAsync = async { shoppingItemApi.getShoppingItems(request) }
            val tagsAsync = async { tagRepository.getOrFetchTags() }
            shoppingItemsAsync.await() to tagsAsync.await()
        }
        return shoppingItems.results
            .map { it.toShoppingItemWithTag(tags) }
            .also {
                _shoppingItems.value = it
            }
    }
}
