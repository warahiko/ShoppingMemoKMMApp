package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toProperties
import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toShoppingItemWithTag
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.ShoppingItemApi
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.AddShoppingItemRequest
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.GetShoppingItemsRequest
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.UpdateItemRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    suspend fun addShoppingItem(shoppingItem: ShoppingItem) {
        val requestBody = shoppingItem.toProperties()
        val request = AddShoppingItemRequest(requestBody)
        val response = withContext(Dispatchers.Default) {
            shoppingItemApi.addShoppingItem(request)
        }
        val item = response.toShoppingItemWithTag(tagRepository.getTags())
        _shoppingItems.value = _shoppingItems.value.orEmpty().plus(item)
    }

    suspend fun updateShoppingItem(vararg shoppingItems: ShoppingItem) {
        val requests = shoppingItems.map { shoppingItem ->
            val properties = shoppingItem.toProperties()
            UpdateItemRequest(properties)
        }
        val responses = withContext(Dispatchers.Default) {
            shoppingItems.zip(requests).map { (shoppingItem, request) ->
                async {
                    shoppingItemApi.updateShoppingItem(shoppingItem.id.toString(), request)
                }
            }.awaitAll()
        }
        val tags = tagRepository.getTags()
        val items = responses.map { it.toShoppingItemWithTag(tags) }
        _shoppingItems.value = _shoppingItems.value?.map { shoppingItem ->
            items.singleOrNull { it.id == shoppingItem.id } ?: shoppingItem
        }
    }

    suspend fun deleteCompletelyShoppingItem(vararg shoppingItems: ShoppingItem) {
        val requests = List(shoppingItems.size) { UpdateItemRequest(isArchived = true) }
        withContext(Dispatchers.Default) {
            shoppingItems.zip(requests).map { (shoppingItem, request) ->
                async {
                    shoppingItemApi.updateShoppingItem(shoppingItem.id.toString(), request)
                }
            }.awaitAll()
        }
        _shoppingItems.value = _shoppingItems.value?.filter {
            it !in shoppingItems
        }
    }
}
