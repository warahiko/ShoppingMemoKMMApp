package io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem

import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.Status
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository

class ArchiveShoppingItemUseCase(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    suspend operator fun invoke(vararg shoppingItems: ShoppingItem) {
        val archived = shoppingItems.map { it.copy(status = Status.ARCHIVED) }
        shoppingItemRepository.updateShoppingItem(*archived.toTypedArray())
    }
}
