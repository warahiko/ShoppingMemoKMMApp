package io.github.warahiko.shoppingmemokmmapplication.android.error

import io.github.warahiko.shoppingmemokmmapplication.error.AppError
import io.github.warahiko.shoppingmemokmmapplication.error.NetworkError
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorCatcher(
    private val errorHolder: ErrorHolder,
) {
    val defaultPolicy = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            // アプリケーションエラー
            is AppError -> {
                errorHolder.notifyError(throwable)
            }
            // ネットワーク関連エラー
            is SocketException,
            is SocketTimeoutException,
            is UnknownHostException,
            -> {
                errorHolder.notifyError(NetworkError)
            }
            else -> {
                throw throwable
            }
        }
    }
}
