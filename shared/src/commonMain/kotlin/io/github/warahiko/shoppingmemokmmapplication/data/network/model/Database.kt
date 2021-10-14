package io.github.warahiko.shoppingmemokmmapplication.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Database(@SerialName("database_id") val id: String)
