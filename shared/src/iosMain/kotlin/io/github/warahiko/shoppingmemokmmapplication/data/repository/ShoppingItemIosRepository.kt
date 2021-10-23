@file:Suppress("unused")

package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.common.asAnyFlow

class ShoppingItemIosRepository(
    shoppingItemRepository: ShoppingItemRepository,
) {
    val shoppingItems = shoppingItemRepository.shoppingItems.asAnyFlow()
    val isFetching = shoppingItemRepository.isFetching.asAnyFlow()
}
