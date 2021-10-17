package io.github.warahiko.shoppingmemokmmapplication.error

expect sealed class AppError() : Exception

data class InternalError(override val message: String) : AppError()

expect object NetworkError : AppError
