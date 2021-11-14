@file:Suppress("unused")

package io.github.warahiko.shoppingmemokmmapplication

import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemIosRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagIosRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.AddShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.EditShoppingItemUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoinIos() = initKoin {
    modules(iosRepositoryModules)
}

private val iosRepositoryModules: Module = module {
    single { ShoppingItemIosRepository(get()) }
    single { TagIosRepository(get()) }
}

class InjectorIos : KoinComponent {
    val shoppingItemRepository: ShoppingItemRepository by inject()
    val tagRepository: TagRepository by inject()

    val shoppingItemIosRepository: ShoppingItemIosRepository by inject()
    val tagIosRepository: TagIosRepository by inject()

    val addShoppingItemUseCase: AddShoppingItemUseCase by inject()
    val editShoppingItemUseCase: EditShoppingItemUseCase by inject()
}
