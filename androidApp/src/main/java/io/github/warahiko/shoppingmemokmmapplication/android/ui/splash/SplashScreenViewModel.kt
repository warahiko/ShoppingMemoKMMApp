package io.github.warahiko.shoppingmemokmmapplication.android.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import kotlinx.coroutines.Job

class SplashScreenViewModel(
    private val shoppingItemRepository: ShoppingItemRepository,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    fun fetchShoppingItems(): Job {
        return viewModelScope.launchSafe {
            shoppingItemRepository.fetchShoppingItems()
        }
    }
}
