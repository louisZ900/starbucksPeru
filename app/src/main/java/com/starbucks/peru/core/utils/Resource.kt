package com.starbucks.peru.core.utils


sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class OnSuccess<T>(data: T): Resource<T>(data)
    class OnError<T>(message: String, data: T? = null): Resource<T>(data, message)
    class OnLoader<T> : Resource<T>()

}