package com.chichi.productlistapp.navigation

@kotlinx.serialization.Serializable
sealed class Routes {
    @kotlinx.serialization.Serializable
    data object HomeScreen : Routes()

    @kotlinx.serialization.Serializable
    data class ProductScreen(val value: String) : Routes()

}