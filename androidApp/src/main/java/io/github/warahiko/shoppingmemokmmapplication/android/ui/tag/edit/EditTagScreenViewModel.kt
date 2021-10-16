package io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ext.withLoading
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository
import io.github.warahiko.shoppingmemokmmapplication.usecase.tag.EditTagUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class EditTagScreenViewModel(
    private val tagRepository: TagRepository,
    private val editTagUseCase: EditTagUseCase,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    val uiModel: StateFlow<UiModel> = tagRepository.tags
        .map { UiModel.from(it.orEmpty()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), UiModel.EMPTY)

    private val _showProgress = MutableStateFlow(false)
    val showProgress: StateFlow<Boolean> get() = _showProgress

    fun getTag(id: String): Tag? {
        return tagRepository.tags.value?.singleOrNull {
            it.id.toString() == id
        }
    }

    fun editTag(tag: Tag): Job {
        return viewModelScope
            .launchSafe {
                editTagUseCase(tag)
            }
            .withLoading(_showProgress)
    }

    data class UiModel(
        val types: List<String>,
    ) {
        companion object {
            val EMPTY = UiModel(emptyList())

            fun from(tags: List<Tag>): UiModel {
                val types = tags
                    .map { it.type }
                    .distinct()
                    .sorted()

                return UiModel(types)
            }
        }
    }
}
