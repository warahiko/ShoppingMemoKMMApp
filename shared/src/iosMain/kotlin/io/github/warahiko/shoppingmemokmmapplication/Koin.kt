@file:Suppress("unused")

package io.github.warahiko.shoppingmemokmmapplication

import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemIosRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagIosRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

fun initKoinIos() = initKoin {}

class InjectorIos : KoinComponent {
    val shoppingItemRepository: ShoppingItemRepository by inject()
    val tagRepository: TagRepository by inject()

    val shoppingItemIosRepository: ShoppingItemIosRepository by inject()
    val tagIosRepository: TagIosRepository by inject()
}
