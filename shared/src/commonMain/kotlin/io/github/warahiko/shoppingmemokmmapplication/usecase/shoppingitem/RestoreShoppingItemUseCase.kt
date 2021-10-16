package io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem

import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.Status
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository

class RestoreShoppingItemUseCase(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    suspend operator fun invoke(shoppingItem: ShoppingItem) {
        val newStatus = if (shoppingItem.doneDate == null) Status.NEW else Status.DONE
        val restored = shoppingItem.copy(status = newStatus)
        shoppingItemRepository.updateShoppingItem(restored)
    }
}
