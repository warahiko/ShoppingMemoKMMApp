package io.github.warahiko.shoppingmemokmmapplication.error

import androidx.annotation.StringRes
import io.github.warahiko.shoppingmemokmmapplication.R

actual sealed class AppError : Exception()

actual object NetworkError : AppError() {
    @StringRes
    val errorMessage: Int = R.string.error_dialog_network_message
}
