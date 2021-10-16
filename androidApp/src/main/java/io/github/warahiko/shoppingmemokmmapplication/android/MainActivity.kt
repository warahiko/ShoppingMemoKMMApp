package io.github.warahiko.shoppingmemokmmapplication.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import io.github.warahiko.shoppingmemokmmapplication.android.ui.ShoppingMemoNavHost
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingMemoAppTheme {
                ShoppingMemoNavHost()
            }
        }
    }
}
