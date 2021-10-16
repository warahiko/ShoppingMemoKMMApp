package io.github.warahiko.shoppingmemokmmapplication.android.di

import io.github.warahiko.shoppingmemokmmapplication.android.error.ErrorCatcher
import io.github.warahiko.shoppingmemokmmapplication.android.error.ErrorHolder
import io.github.warahiko.shoppingmemokmmapplication.android.error.ErrorMonitor
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafeImpl
import io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.add.AddShoppingItemScreenViewModel
import io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.edit.EditShoppingItemScreenViewModel
import io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list.ShoppingItemListScreenViewModel
import io.github.warahiko.shoppingmemokmmapplication.android.ui.splash.SplashScreenViewModel
import io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.add.AddTagScreenViewModel
import io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.edit.EditTagScreenViewModel
import io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.list.TagListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val androidModules: Module = module {
    viewModel { SplashScreenViewModel(get(), get()) }

    viewModel { ShoppingItemListScreenViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { AddShoppingItemScreenViewModel(get(), get(), get()) }
    viewModel { EditShoppingItemScreenViewModel(get(), get(), get(), get()) }

    viewModel { TagListScreenViewModel(get(), get(), get()) }
    viewModel { AddTagScreenViewModel(get(), get(), get()) }
    viewModel { EditTagScreenViewModel(get(), get(), get()) }
}

val androidErrorHandlingModules: Module = module {
    single { ErrorHolder() }
    single { ErrorCatcher(get()) }
    single { ErrorMonitor(get()) }
    single<LaunchSafe> { LaunchSafeImpl(get()) }
}
