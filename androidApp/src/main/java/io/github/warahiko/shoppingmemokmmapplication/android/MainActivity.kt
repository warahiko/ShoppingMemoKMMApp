package io.github.warahiko.shoppingmemokmmapplication.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemListRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagListRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val shoppingItemListRepository: ShoppingItemListRepository by inject()
    private val tagListRepository: TagListRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val tv: TextView = findViewById(R.id.text_view)
            tv.text =
                shoppingItemListRepository.fetchShoppingList().take(2).fold("") { l, r ->
                    "$l, $r"
                } + tagListRepository.fetchTagList().take(2).fold("") { l, r ->
                    "$l, $r"
                }
        }
    }
}
