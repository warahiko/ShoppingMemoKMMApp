package io.github.warahiko.shoppingmemokmmapplication.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemListRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val shoppingItemListRepository: ShoppingItemListRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val tv: TextView = findViewById(R.id.text_view)
            tv.text =
                shoppingItemListRepository.fetchShoppingItemList().take(5).fold("") { l, r ->
                    "$l, $r"
                }
        }
    }
}
