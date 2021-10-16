package io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem

import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.Status
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository

class DeleteShoppingItemUseCase(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    suspend operator fun invoke(shoppingItem: ShoppingItem) {
        val deleted = shoppingItem.copy(status = Status.DELETED)
        shoppingItemRepository.updateShoppingItem(deleted)
    }
}
