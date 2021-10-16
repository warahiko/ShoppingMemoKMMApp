package io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ext.withLoading
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository
import io.github.warahiko.shoppingmemokmmapplication.usecase.tag.DeleteTagUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TagListScreenViewModel(
    private val tagRepository: TagRepository,
    private val deleteTagUseCase: DeleteTagUseCase,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    val uiModel: StateFlow<UiModel> = tagRepository.tags
        .map { UiModel.from(it.orEmpty()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), UiModel.EMPTY)

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    private val _deleteEvent = MutableStateFlow<DeleteEvent?>(null)
    val deleteEvent: StateFlow<DeleteEvent?> get() = _deleteEvent

    fun refreshTags(): Job {
        return viewModelScope
            .launchSafe {
                tagRepository.fetchTags()
            }
            .withLoading(_isRefreshing)
    }

    fun showDeleteTagConfirmationDialog(tag: Tag) {
        _deleteEvent.value = DeleteEvent.ShowConfirmationDialog(tag)
    }

    fun dismissDeleteTagConfirmationDialog() {
        _deleteEvent.value = null
    }

    fun deleteTag(tag: Tag) = viewModelScope.launchSafe {
        _deleteEvent.value = DeleteEvent.ShowProgressDialog
        deleteTagUseCase(tag)
        _deleteEvent.value = null
    }

    data class UiModel(
        val tagsGroupedByType: Map<String, List<Tag>>
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

    sealed class DeleteEvent {
        data class ShowConfirmationDialog(val tag: Tag) : DeleteEvent()
        object ShowProgressDialog : DeleteEvent()
    }
}
