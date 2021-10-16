package io.github.warahiko.shoppingmemokmmapplication.data.network.model

import io.github.warahiko.shoppingmemokmmapplication.BuildKonfig
import kotlinx.serialization.Serializable

@Serializable
class AddShoppingItemRequest private constructor(
    @Suppress("unused") val parent: Database,
    val properties: Map<String, Property>,
) {
    constructor(properties: Map<String, Property>) : this(
        Database(BuildKonfig.DATABASE_ID),
        properties,
    )
}
