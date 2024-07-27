package com.chichi.productlistapp.model

data class CartItem(
    val products: MutableList<Product> = mutableListOf(), // Default values
    val total: Pair<Double, Int> = Pair(0.00, 0)
)