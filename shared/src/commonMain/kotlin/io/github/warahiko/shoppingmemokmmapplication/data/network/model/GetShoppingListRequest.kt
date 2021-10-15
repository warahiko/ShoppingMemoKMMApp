package io.github.warahiko.shoppingmemokmmapplication.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class GetShoppingListRequest(
    val filter: Filter? = null,
)
