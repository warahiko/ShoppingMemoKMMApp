package io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem

import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.Status
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

class ChangeShoppingItemIsDoneUseCase(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    suspend operator fun invoke(
        shoppingItem: ShoppingItem,
        newIsDone: Boolean,
    ) {
        val newStatus = if (newIsDone) Status.DONE else Status.NEW
        val newDoneDate = if (newIsDone) Clock.System.todayAt(TimeZone.currentSystemDefault()) else null
        val newShoppingItem = shoppingItem.copy(status = newStatus, doneDate = newDoneDate)
        shoppingItemRepository.updateShoppingItem(newShoppingItem)
    }
}
