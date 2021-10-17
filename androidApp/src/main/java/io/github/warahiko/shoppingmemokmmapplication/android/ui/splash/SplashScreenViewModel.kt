package io.github.warahiko.shoppingmemokmmapplication.android.ui.splash

import androidx.lifecycle.ViewModel
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ExternalScope
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import kotlinx.coroutines.Job

class SplashScreenViewModel(
    private val shoppingItemRepository: ShoppingItemRepository,
    private val externalScope: ExternalScope,
    launchSafe: LaunchSafe,
) : ViewModel(), LaunchSafe by launchSafe {

    fun fetchShoppingItems(): Job {
        return externalScope.launchSafe {
            shoppingItemRepository.fetchShoppingItems()
        }
    }
}
