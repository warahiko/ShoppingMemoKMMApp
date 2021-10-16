package io.github.warahiko.shoppingmemokmmapplication.error

import androidx.annotation.StringRes
import io.github.warahiko.shoppingmemokmmapplication.R

actual object NetworkError : AppError() {
    @StringRes
    val errorMessage: Int = R.string.error_dialog_network_message
}
