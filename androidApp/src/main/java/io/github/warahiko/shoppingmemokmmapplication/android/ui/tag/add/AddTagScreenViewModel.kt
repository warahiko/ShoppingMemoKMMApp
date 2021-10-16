package io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ext.withLoading
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository
import io.github.warahiko.shoppingmemokmmapplication.usecase.tag.AddTagUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AddTagScreenViewModel(
    tagRepository: TagRepository,
    private val addTagUseCase: AddTagUseCase,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    val uiModel: StateFlow<UiModel> = tagRepository.tags
        .map { UiModel.from(it.orEmpty()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), UiModel.EMPTY)

    private val _showProgress = MutableStateFlow(false)
    val showProgress: StateFlow<Boolean> get() = _showProgress

    fun addTag(tag: Tag): Job {
        return viewModelScope
            .launchSafe {
                addTagUseCase(tag)
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
