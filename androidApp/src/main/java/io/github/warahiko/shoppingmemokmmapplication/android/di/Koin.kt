package io.github.warahiko.shoppingmemokmmapplication.android.di

import io.github.warahiko.shoppingmemokmmapplication.android.error.ErrorCatcher
import io.github.warahiko.shoppingmemokmmapplication.android.error.ErrorHolder
import io.github.warahiko.shoppingmemokmmapplication.android.error.ErrorMonitor
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafeImpl
import io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list.ShoppingItemListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val androidModules: Module = module {
    viewModel { ShoppingItemListScreenViewModel(get(), get()) }
}

val androidErrorHandlingModules: Module = module {
    single { ErrorHolder() }
    single { ErrorCatcher(get()) }
    single { ErrorMonitor(get()) }
    single<LaunchSafe> { LaunchSafeImpl(get()) }
}
