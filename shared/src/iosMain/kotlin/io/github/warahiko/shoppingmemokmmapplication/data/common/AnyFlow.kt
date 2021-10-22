@file:Suppress("unused")

package io.github.warahiko.shoppingmemokmmapplication.data.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class AnyFlow<T>(source: Flow<T>) : Flow<T> by source {
    fun collect(
        onEach: (value: T) -> Unit,
        onCompletion: (cause: Throwable?) -> Unit,
    ): Cancelable {
        val scope = CoroutineScope(Job() + Dispatchers.Main)

        onEach { onEach(it) }
            .catch { onCompletion(it) }
            .onCompletion { onCompletion(null) }
            .launchIn(scope)

        return object : Cancelable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }

    fun collect(
        onEach: (value: T) -> Unit,
    ): Cancelable {
        val scope = CoroutineScope(Job() + Dispatchers.Main)

        onEach { onEach(it) }.launchIn(scope)

        return object : Cancelable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }
}

fun <T> Flow<T>.asAnyFlow(): AnyFlow<T> = AnyFlow(this)
