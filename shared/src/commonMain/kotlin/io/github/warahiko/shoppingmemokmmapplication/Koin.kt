package io.github.warahiko.shoppingmemokmmapplication

import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemListRepository
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(modules)
    }
}

private val modules: Module = module {
    single { ShoppingItemListRepository("Api") }
}

@Suppress("unused")
fun initKoinIos() = initKoin {}

@Suppress("unused")
class InjectorIos : KoinComponent {
    val shoppingItemListRepository: ShoppingItemListRepository by inject()
}
