package io.github.warahiko.shoppingmemokmmapplication.android.error

import io.github.warahiko.shoppingmemokmmapplication.android.common.Event
import io.github.warahiko.shoppingmemokmmapplication.android.common.toEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class ErrorHolder {

    // `onBufferOverflow` がデフォルトの場合、tryEmit がsuspend になって失敗するときがある
    private val _error = MutableSharedFlow<Event<AppError>>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val error: SharedFlow<Event<AppError>>
        get() = _error

    // CoroutineExceptionHandler ではsuspend が呼びづらい
    fun notifyError(error: AppError) {
        check(_error.tryEmit(error.toEvent()))
    }
}
