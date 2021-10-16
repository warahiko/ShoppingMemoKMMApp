package io.github.warahiko.shoppingmemokmmapplication.android

import android.app.ActivityManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.color.MaterialColors
import io.github.warahiko.shoppingmemokmmapplication.android.ui.ShoppingMemoNavHost
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRecentAppsAppearance()

        setContent {
            ShoppingMemoAppTheme {
                ShoppingMemoNavHost()
            }
        }
    }

    /**
     * 最近使ったアプリでの見た目を変更する
     */
    private fun setRecentAppsAppearance() {
        @Suppress("Deprecation")
        val taskDescription = ActivityManager.TaskDescription(
            null,
            null,
            MaterialColors.getColor(this, R.attr.colorPrimarySurface, 0),
        )
        setTaskDescription(taskDescription)
    }
}
