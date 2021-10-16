package io.github.warahiko.shoppingmemokmmapplication.android.error

import androidx.annotation.StringRes
import io.github.warahiko.shoppingmemokmmapplication.android.R

sealed class AppError : Exception()

data class InternalError(override val message: String) : AppError()

object NetworkError : AppError() {
    @StringRes
    val errorMessage: Int = R.string.error_dialog_network_message
}
