package io.github.warahiko.shoppingmemokmmapplication.data.mapper

import com.benasher44.uuid.uuidFrom
import io.github.warahiko.shoppingmemokmmapplication.data.ext.concatText
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.Property
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.Select
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.TagPage

fun TagPage.toTag(): Tag {
    return Tag(
        id = uuidFrom(this.id),
        name = checkNotNull(properties[TagProperty.Name.key]?.title?.concatText()),
        type = checkNotNull(properties[TagProperty.Type.key]?.select?.name),
    )
}

fun Tag.toProperties(): Map<String, Property> {
    return mapOf(
        TagProperty.Name.key to Property(title = name.toRichTextList()),
        TagProperty.Type.key to Property(select = Select(type)),
    )
}

private enum class TagProperty(val key: String) {
    Name("Name"),
    Type("Type"),
}
