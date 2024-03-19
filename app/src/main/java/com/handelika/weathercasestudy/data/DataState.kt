package com.handelika.weathercasestudy.data

sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Loading<T>(val loading: Boolean) : DataState<T>()
    data class Error<T>(val exception: Exception) : DataState<T>()
}
