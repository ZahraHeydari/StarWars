package com.android.domain.util

sealed class ResultOf<T : Any> {
    class Nothing<T : Any> : ResultOf<T>()
    class Loading<T : Any> : ResultOf<T>()
    class Success<T : Any>(val value: T) : ResultOf<T>()
    class Failure<T : Any>(val throwable: Throwable) : ResultOf<T>()
}