package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ShoppingItemListScreenViewModel(
    private val shoppingItemRepository: ShoppingItemRepository,
) : ViewModel() {

    val shoppingItems = shoppingItemRepository.shoppingItems
        .map { shoppingItems -> shoppingItems?.joinToString { "$it" } ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), "")

    fun fetchShoppingItems(): Job {
        return viewModelScope.launch {
            shoppingItemRepository.fetchShoppingItems()
        }
    }
}
