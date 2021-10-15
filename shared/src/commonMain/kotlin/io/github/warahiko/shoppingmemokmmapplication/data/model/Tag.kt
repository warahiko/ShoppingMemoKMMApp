package io.github.warahiko.shoppingmemokmmapplication.data.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

data class Tag(
    val id: Uuid,
    val name: String,
    val type: String,
) {
    override fun toString() = "$type > $name"

    companion object {
        fun newInstance(
            name: String = "",
            type: String = "",
        ): Tag {
            return Tag(
                id = uuid4(),
                name = name,
                type = type,
            )
        }
    }
}
