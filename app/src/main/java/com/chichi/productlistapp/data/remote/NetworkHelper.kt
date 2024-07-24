package com.chichi.productlistapp.data.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException


object NetworkHelper {

    const val TEST_NETWORK_TIMER = 1500
    const val TEST_NETWORK_HOST_NAME = "www.google.com"
    const val TEST_NETWORK_PORT = 80
    const val NETWORK_ERROR_MESSAGE = "Network error, please try again"
    const val NO_INTERNET_MESSAGE = "Looks like you have no internet connection"
    const val SERVER_ERROR_MESSAGE = "Unknown server error, please try again later"
    const val CLIENT_ERROR_MESSAGE = "Something went wrong!"

    inline fun <reified T> convertErrorBody(t: HttpException): T? {
        return try {
            t.response()?.errorBody()?.let {
                val type = object : TypeToken<T>() {}.type
                Gson().fromJson(it.charStream(), type)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}