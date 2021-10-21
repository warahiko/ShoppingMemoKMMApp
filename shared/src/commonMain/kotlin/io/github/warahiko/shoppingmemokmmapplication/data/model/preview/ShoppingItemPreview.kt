package io.github.warahiko.shoppingmemokmmapplication.data.model.preview

import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.Status
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import kotlinx.datetime.toLocalDate

object ShoppingItemPreview {

    val sample = ShoppingItem.newInstance(
        name = "にんじん",
        count = 1,
        status = Status.DONE,
        doneDate = "2021-10-16".toLocalDate(),
        memo = "メモ",
        tag = Tag.newInstance(name = "にんじん", type = "野菜"),
    )

    val samples = listOf(
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

    // SortedMap はkotlin にはないため使えない
    val sampleMap = samples
        .sortedBy { it.tag?.type }
        .groupBy { it.tag?.type.orEmpty() }
        .mapValues { map ->
            map.value.sortedBy { it.name }
        }
}
