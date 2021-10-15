package io.github.warahiko.shoppingmemokmmapplication.data.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.datetime.LocalDate

data class ShoppingItem(
    val id: Uuid,
    val name: String,
    val count: Int,
    val status: Status,
    val doneDate: LocalDate?,
    val memo: String,
    val tag: Tag?,
) {

    val isDone: Boolean get() = status == Status.DONE

    fun toEditable(): ShoppingItemEditable {
        return ShoppingItemEditable(
            id = id,
            name = name,
            count = count.toString(),
            status = status,
            doneDate = doneDate,
            memo = memo,
            tag = tag,
        )
    }

    companion object {
        fun newInstance(
            name: String,
            count: Int,
            status: Status,
            doneDate: LocalDate?,
            memo: String,
            tag: Tag?,
        ): ShoppingItem {
            return ShoppingItem(id = uuid4(), name, count, status, doneDate, memo, tag)
        }
    }
}

data class ShoppingItemEditable(
    val id: Uuid,
    val name: String,
    val count: String,
    val status: Status,
    val doneDate: LocalDate?,
    val memo: String,
    val tag: Tag?,
) {
    fun fix(): ShoppingItem {
        return ShoppingItem(
            id = id,
            name = name,
            count = count.toInt(),
            status = status,
            doneDate = doneDate,
            memo = memo,
            tag = tag,
        )
    }

    companion object {
        fun newInstanceToAdd(): ShoppingItemEditable {
            return ShoppingItemEditable(
                id = uuid4(),
                name = "",
                count = "1",
                status = Status.NEW,
                doneDate = null,
                memo = "",
                tag = null,
            )
        }
    }
}
