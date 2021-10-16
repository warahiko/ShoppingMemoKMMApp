package io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem

import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository

class DeleteCompletelyShoppingItemUseCase(
    private val shoppingItemRepository: ShoppingItemRepository,
) {
    suspend operator fun invoke(vararg shoppingItems: ShoppingItem) {
        shoppingItemRepository.deleteCompletelyShoppingItem(*shoppingItems)
    }
}
