package io.github.warahiko.shoppingmemokmmapplication.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BottomNavHostViewModel(
    shoppingItemRepository: ShoppingItemRepository,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    val bottomTabIsEnabled: StateFlow<Boolean> = shoppingItemRepository.shoppingItems
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), false)
}
