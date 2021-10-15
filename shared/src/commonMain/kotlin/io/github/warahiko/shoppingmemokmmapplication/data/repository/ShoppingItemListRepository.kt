package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toShoppingItemWithTag
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.ShoppingItemListApi
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.GetShoppingListRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class ShoppingItemListRepository constructor(
    private val shoppingItemListApi: ShoppingItemListApi,
    private val tagListRepository: TagListRepository,
) {

    private val _shoppingList: MutableStateFlow<List<ShoppingItem>?> = MutableStateFlow(null)
    val shoppingList: StateFlow<List<ShoppingItem>?> get() = _shoppingList

    suspend fun fetchShoppingItemList(): List<ShoppingItem> {
        val request = GetShoppingListRequest()
        val (shoppingItemList, tagList) = withContext(Dispatchers.Default) {
            val shoppingItemListAsync = async { shoppingItemListApi.getShoppingList(request) }
            val tagListAsync = async { tagListRepository.getOrFetchTagList() }
            shoppingItemListAsync.await() to tagListAsync.await()
        }
        return shoppingItemList.results
            .map { it.toShoppingItemWithTag(tagList) }
            .also {
                _shoppingList.value = it
            }
    }
}
