package com.example.locker.data

sealed class ResultState<out R> private constructor() {
    data class Success<out T>(var data: @UnsafeVariance T) : ResultState<T>()
    data class Error(val error: String) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}