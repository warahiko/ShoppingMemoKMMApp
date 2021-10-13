package io.github.warahiko.shoppingmemokmmapplication.data.repository

// TODO: dummy
class ShoppingItemListRepository constructor(
    private val shoppingItemListApi: String,
) {
    fun fetchShoppingList(): List<String> {
        return listOf(
            shoppingItemListApi,
            "にんじん",
            "玉ねぎ",
            "牛乳",
        )
    }
}
