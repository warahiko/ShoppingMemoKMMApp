package io.github.warahiko.shoppingmemokmmapplication.data.network.model

import io.github.warahiko.shoppingmemokmmapplication.BuildKonfig
import kotlinx.serialization.Serializable

@Serializable
class AddTagRequest private constructor(
    @Suppress("unused") val parent: Database,
    val properties: Map<String, Property>,
) {
    constructor(properties: Map<String, Property>) : this(
        Database(BuildKonfig.TAG_DATABASE_ID),
        properties,
    )
}
