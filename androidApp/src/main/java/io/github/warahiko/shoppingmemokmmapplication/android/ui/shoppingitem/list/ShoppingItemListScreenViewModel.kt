package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ShoppingItemListScreenViewModel(
    private val shoppingItemRepository: ShoppingItemRepository,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    val uiModel = shoppingItemRepository.shoppingItems
        .map { UiModel.from(it.orEmpty()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), UiModel.EMPTY)

    fun fetchShoppingItems(): Job {
        return viewModelScope.launchSafe {
            shoppingItemRepository.fetchShoppingItems()
        }
    }

    data class UiModel(
        val mainShoppingItems: Map<String, List<ShoppingItem>>,
        val archivedShoppingItems: Map<String, List<ShoppingItem>>,
        val deletedShoppingItems: List<ShoppingItem>,
    ) {
        companion object {
            val EMPTY = UiModel(emptyMap(), emptyMap(), emptyList())

            fun from(shoppingItems: List<ShoppingItem>): UiModel {
                val mainShoppingItems = shoppingItems
                    .filter { it.status in ShoppingItemListTab.Main.statusList }
                    .groupBy { it.tag?.type.orEmpty() }
                    .toSortedMap()
                    .mapValues { map ->
                        map.value.sortedBy { it.name }
                    }

                val archivedShoppingItems = shoppingItems
                    .filter { it.status in ShoppingItemListTab.Archived.statusList }
                    .groupBy { it.doneDate?.toString().orEmpty() }
                    .toSortedMap(compareByDescending { it })
                    .mapValues { map ->
                        map.value.sortedBy { it.name }
                    }

                val deletedShoppingItems = shoppingItems
                    .filter { it.status in ShoppingItemListTab.Deleted.statusList }
                    .sortedBy { it.name }

                return UiModel(mainShoppingItems, archivedShoppingItems, deletedShoppingItems)
            }
        }
    }
}
