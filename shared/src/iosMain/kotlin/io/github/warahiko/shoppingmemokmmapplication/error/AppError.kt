package io.github.warahiko.shoppingmemokmmapplication.error

actual sealed class AppError : Exception()

actual object NetworkError : AppError()
