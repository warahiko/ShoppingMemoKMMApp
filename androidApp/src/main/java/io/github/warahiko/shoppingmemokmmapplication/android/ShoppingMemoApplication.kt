package io.github.warahiko.shoppingmemokmmapplication.android

import android.app.Application
import io.github.warahiko.shoppingmemokmmapplication.android.di.androidModules
import io.github.warahiko.shoppingmemokmmapplication.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class ShoppingMemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@ShoppingMemoApplication)
            modules(androidModules)
        }
    }
}
