package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ext.withLoading
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.ArchiveShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.ChangeShoppingItemIsDoneUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.DeleteCompletelyShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.DeleteShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.RestoreShoppingItemUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ShoppingItemListScreenViewModel(
    private val shoppingItemRepository: ShoppingItemRepository,
    private val changeShoppingItemIsDoneUseCase: ChangeShoppingItemIsDoneUseCase,
    private val archiveShoppingItemUseCase: ArchiveShoppingItemUseCase,
    private val deleteShoppingItemUseCase: DeleteShoppingItemUseCase,
    private val restoreShoppingItemUseCase: RestoreShoppingItemUseCase,
    private val deleteCompletelyShoppingItemUseCase: DeleteCompletelyShoppingItemUseCase,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    val uiModel = shoppingItemRepository.shoppingItems
        .map { UiModel.from(it.orEmpty()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), UiModel.EMPTY)

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    private val _deleteEvent = MutableStateFlow<DeleteEvent?>(null)
    val deleteEvent: StateFlow<DeleteEvent?> get() = _deleteEvent

    fun fetchShoppingItems(): Job {
        return viewModelScope.launchSafe {
            shoppingItemRepository.fetchShoppingItems()
        }
    }

    fun refreshShoppingItems(): Job {
        return fetchShoppingItems().withLoading(_isRefreshing)
    }

    fun changeShoppingItemIsDone(shoppingItem: ShoppingItem): Job {
        return viewModelScope.launchSafe {
            changeShoppingItemIsDoneUseCase(shoppingItem, !shoppingItem.isDone)
        }
    }

    fun archiveShoppingItem(shoppingItem: ShoppingItem): Job {
        return viewModelScope.launchSafe {
            archiveShoppingItemUseCase(shoppingItem)
        }
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem): Job {
        return viewModelScope.launchSafe {
            deleteShoppingItemUseCase(shoppingItem)
        }
    }

    fun restoreShoppingItem(shoppingItem: ShoppingItem): Job {
        return viewModelScope.launchSafe {
            restoreShoppingItemUseCase(shoppingItem)
        }
    }

    fun archiveAllDone(): Job {
        return viewModelScope.launchSafe {
            val doneList = uiModel.value.mainShoppingItems.values.flatten().filter { it.isDone }
            archiveShoppingItemUseCase(*doneList.toTypedArray())
        }
    }

    fun showDeleteCompletelyConfirmationDialog() {
        _deleteEvent.value = DeleteEvent.ShowConfirmationDialog
    }

    fun dismissDeleteCompletelyConfirmationDialog() {
        _deleteEvent.value = null
    }

    fun deleteCompletelyShoppingItems() = viewModelScope.launchSafe {
        _deleteEvent.value = DeleteEvent.ShowProgressDialog
        deleteCompletelyShoppingItemUseCase(*uiModel.value.deletedShoppingItems.toTypedArray())
        _deleteEvent.value = null
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

    enum class DeleteEvent {
        ShowConfirmationDialog,
        ShowProgressDialog,
    }
}
