package com.chichi.productlistapp.data.remote

import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.UnknownHostException

object GeneralErrorHandlerImpl {

    inline fun <reified T> getError(throwable: Throwable): BaseWrapper {
        return when (throwable) {
            is NoConnectivityException -> BaseWrapper.Error(throwable.message)
            is UnknownHostException -> BaseWrapper.Error(NetworkHelper.NO_INTERNET_MESSAGE)

            is HttpException -> {
                BaseWrapper.Error(NetworkHelper.SERVER_ERROR_MESSAGE)
            }

            is InterruptedIOException -> BaseWrapper.Error(NetworkHelper.SERVER_ERROR_MESSAGE)
            is SocketException -> BaseWrapper.Error(NetworkHelper.NETWORK_ERROR_MESSAGE)

            else -> {
                throwable.printStackTrace()
                BaseWrapper.Error("Error: ${throwable.message}")
            }
        }
    }
}