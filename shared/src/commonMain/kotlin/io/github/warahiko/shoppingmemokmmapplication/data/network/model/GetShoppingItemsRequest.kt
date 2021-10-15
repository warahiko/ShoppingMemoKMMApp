package io.github.warahiko.shoppingmemokmmapplication.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class GetShoppingItemsRequest(
    val filter: Filter? = null,
)
