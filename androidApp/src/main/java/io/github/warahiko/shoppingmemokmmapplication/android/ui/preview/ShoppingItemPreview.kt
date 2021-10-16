package io.github.warahiko.shoppingmemokmmapplication.android.ui.preview

import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.Status
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import kotlinx.datetime.toLocalDate

object ShoppingItemPreview {

    fun getSample() = ShoppingItem.newInstance(
        name = "にんじん",
        count = 1,
        status = Status.DONE,
        doneDate = "2021-10-16".toLocalDate(),
        memo = "メモ",
        tag = Tag.newInstance(name = "にんじん", type = "野菜"),
    )

    fun getSampleList() = listOf(
        ShoppingItem.newInstance(
            name = "にんじん",
            count = 1,
            status = Status.DONE,
            doneDate = "2021-10-16".toLocalDate(),
            memo = "memo",
            tag = Tag.newInstance(name = "にんじん", type = "野菜"),
        ),
        ShoppingItem.newInstance(
            name = "たまねぎ",
            count = 2,
            status = Status.NEW,
            doneDate = "2021-10-16".toLocalDate(),
            memo = "",
            tag = Tag.newInstance(name = "たまねぎ", type = "野菜"),
        ),
        ShoppingItem.newInstance(
            name = "卵",
            count = 1,
            status = Status.NEW,
            doneDate = "2021-10-16".toLocalDate(),
            memo = "memo",
            tag = Tag.newInstance(name = "卵", type = "肉"),
        ),
        ShoppingItem.newInstance(
            name = "牛乳",
            count = 3,
            status = Status.DONE,
            doneDate = "2021-10-16".toLocalDate(),
            memo = "",
            tag = Tag.newInstance(name = "牛乳", type = "飲料"),
        ),
    ).sortedBy {
        it.name
    }

    fun getSampleMap() = getSampleList()
        .groupBy { it.tag?.type.orEmpty() }
        .toSortedMap()
        .mapValues { map ->
            map.value.sortedBy { it.name }
        }
}
