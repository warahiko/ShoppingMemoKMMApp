package io.github.warahiko.shoppingmemokmmapplication.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val shoppingItemRepository: ShoppingItemRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(shoppingItemRepository)
        }
    }
}

@Composable
private fun MainScreen(
    shoppingItemRepository: ShoppingItemRepository,
) {
    val shoppingItems by shoppingItemRepository.shoppingItems.collectAsState()
    val text by remember(shoppingItems) {
        derivedStateOf {
            shoppingItems?.take(5)
                ?.joinToString {
                    it.toString()
                }
                ?: ""
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text,
            modifier = Modifier.align(Alignment.Center),
        )
    }

    LaunchedEffect(Unit) {
        shoppingItemRepository.fetchShoppingItems()
    }
}
