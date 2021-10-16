package io.github.warahiko.shoppingmemokmmapplication.android

import android.app.Application
import io.github.warahiko.shoppingmemokmmapplication.android.di.androidErrorHandlingModules
import io.github.warahiko.shoppingmemokmmapplication.android.di.androidModules
import io.github.warahiko.shoppingmemokmmapplication.android.error.ErrorMonitor
import io.github.warahiko.shoppingmemokmmapplication.initKoin
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class ShoppingMemoApplication : Application() {

    private val errorMonitor: ErrorMonitor by inject()

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@ShoppingMemoApplication)
            modules(androidModules, androidErrorHandlingModules)
        }

        registerActivityLifecycleCallbacks(errorMonitor)
    }

    override fun onTerminate() {
        unregisterActivityLifecycleCallbacks(errorMonitor)
        super.onTerminate()
    }
}
