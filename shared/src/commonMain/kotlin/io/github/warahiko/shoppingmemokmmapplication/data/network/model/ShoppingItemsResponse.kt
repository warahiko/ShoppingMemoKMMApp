package io.github.warahiko.shoppingmemokmmapplication.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingItemsResponse(
    val results: List<ShoppingItemPage>,
)

@Serializable
data class ShoppingItemPage(
    val id: String,
    val properties: Map<String, Property>,
)
