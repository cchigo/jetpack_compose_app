package com.chichi.productlistapp.data.remote

sealed class BaseWrapper {
    object Empty : BaseWrapper()
    object Loading : BaseWrapper()
    data class Success<out S>(val result: S) : BaseWrapper()
    data class Error(val message: String) : BaseWrapper()
}

fun <R> BaseWrapper.get(): R? {
    if (this is BaseWrapper.Success<*>) {
        return result as? R
    }
    return null
}



