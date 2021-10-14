package io.github.warahiko.shoppingmemokmmapplication.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AddShoppingItemRequest(
    val parent: Database,
    val properties: Map<String, Property>,
)
