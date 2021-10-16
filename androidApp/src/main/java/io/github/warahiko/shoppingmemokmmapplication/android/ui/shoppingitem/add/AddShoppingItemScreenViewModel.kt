package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ext.withLoading
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.AddShoppingItemUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AddShoppingItemScreenViewModel(
    tagRepository: TagRepository,
    private val addShoppingItemUseCase: AddShoppingItemUseCase,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    val uiModel: StateFlow<UiModel> = tagRepository.tags
        .map { UiModel.from(it.orEmpty()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), UiModel.EMPTY)

    private val _showProgress = MutableStateFlow(false)
    val showProgress: StateFlow<Boolean> get() = _showProgress

    fun addShoppingItem(shoppingItem: ShoppingItem): Job {
        return viewModelScope
            .launchSafe {
                addShoppingItemUseCase(shoppingItem)
            }
            .withLoading(_showProgress)
    }

    data class UiModel(
        val tagsGroupedByType: Map<String, List<Tag>>,
    ) {
        companion object {
            val EMPTY = UiModel(emptyMap())

            fun from(tags: List<Tag>): UiModel {
                val tagsGroupedByType = tags
                    .groupBy { it.type }
                    .toSortedMap()
                    .mapValues { map ->
                        map.value.sortedBy { it.name }
                    }

                return UiModel(tagsGroupedByType)
            }
        }
    }
}
