package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import androidx.annotation.StringRes
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.data.model.Status

enum class ShoppingItemListTab(
    @StringRes val titleResId: Int,
    val statusList: List<Status>,
) {
    Main(R.string.shopping_item_list_tab_main, listOf(Status.NEW, Status.DONE)),
    Archived(R.string.shopping_item_list_tab_archived, listOf(Status.ARCHIVED)),
    Deleted(R.string.shopping_item_list_tab_deleted, listOf(Status.DELETED)),
}
