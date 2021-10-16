package io.github.warahiko.shoppingmemokmmapplication.android.common

class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    fun peekContent(): T = content
}

fun <T> T.toEvent(): Event<T> = Event(this)
