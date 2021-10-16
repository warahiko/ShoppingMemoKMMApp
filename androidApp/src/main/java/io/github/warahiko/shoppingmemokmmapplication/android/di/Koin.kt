package io.github.warahiko.shoppingmemokmmapplication.android.di

import io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list.ShoppingItemListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val androidModules: Module = module {
    viewModel { ShoppingItemListScreenViewModel(get()) }
}
