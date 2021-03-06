package io.github.warahiko.shoppingmemokmmapplication.data.mapper

import com.benasher44.uuid.uuidFrom
import io.github.warahiko.shoppingmemokmmapplication.data.ext.concatText
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.Status
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.Date
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.Property
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.Relation
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.Select
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.ShoppingItemPage
import kotlinx.datetime.toLocalDate

fun ShoppingItemPage.toShoppingItem(): ShoppingItem {
    return ShoppingItem(
        id = uuidFrom(id),
        name = checkNotNull(properties[ShoppingItemProperty.Name.key]?.title?.concatText()),
        count = checkNotNull(properties[ShoppingItemProperty.Count.key]?.number?.toInt()),
        status = checkNotNull(properties[ShoppingItemProperty.Status.key]?.select?.let {
            Status.from(it.name)
        }),
        doneDate = properties[ShoppingItemProperty.DoneDate.key]?.date?.start?.toLocalDate(),
        memo = checkNotNull(properties[ShoppingItemProperty.Memo.key]?.richTexts?.concatText()),
        tag = null,
    )
}

fun ShoppingItemPage.toShoppingItemWithTag(tags: List<Tag>): ShoppingItem {
    val item = this.toShoppingItem()
    val relationId = relations.firstOrNull()?.id ?: return item
    val tag = tags.singleOrNull { it.id.toString() == relationId }
    return item.copy(tag = tag)
}

val ShoppingItemPage.relations: List<Relation>
    get() = checkNotNull(properties[ShoppingItemProperty.Tag.key]?.relations)

fun ShoppingItem.toProperties(): Map<String, Property> {
    return mutableMapOf(
        ShoppingItemProperty.Name.key to Property(title = name.toRichTextList()),
        ShoppingItemProperty.Count.key to Property(number = count.toLong()),
        ShoppingItemProperty.Status.key to Property(select = Select(status.text)),
        ShoppingItemProperty.DoneDate.key to Property(
            date = Date(
                start = doneDate?.toString().orEmpty()
            )
        ),
        ShoppingItemProperty.Memo.key to Property(richTexts = memo.toRichTextList()),
    ).let { map ->
        tag?.let { tag ->
            map += ShoppingItemProperty.Tag.key to Property(relations = listOf(Relation(tag.id.toString())))
        }
        map
    }
}

private enum class ShoppingItemProperty(val key: String) {
    Name("Name"),
    Count("Count"),
    Status("Status"),
    DoneDate("DoneDate"),
    Memo("Memo"),
    Tag("Tag"),
}
