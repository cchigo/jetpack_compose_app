package com.chichi.productlistapp.util

import androidx.compose.runtime.mutableStateListOf
import com.chichi.productlistapp.model.CartItem
import com.chichi.productlistapp.model.Product
import javax.inject.Inject

class CartManager @Inject constructor() {

    private val cartActions = mutableStateListOf<Product>()

    /** Adds product to cart, if it exist, updates `selectedQuantity`**/
    fun addProductToCart(product: Product): CartItem {
        val index = cartActions.indexOfFirst { it.id == product.id }
        if (index != -1) {
            cartActions[index] = cartActions[index].copy(selectedQty = product.selectedQty, selectedAmount = product.selectedAmount )
        } else {
            cartActions.add(product.copy(selectedQty = product.selectedQty, selectedAmount = product.selectedAmount ))
        }
        return CartItem(cartActions, computeTotalAmount())
    }

    /** Inserts product to cart, and, updates `selectedQuantity`**/
    fun insertProduct(product: Product): CartItem {
        val new = product.copy(
            selectedQty = product.selectedQty,
            selectedAmount = product.selectedAmount // w
        )
        cartActions.add(new)
        return CartItem(cartActions, computeTotalAmount())
    }

    /** Removes product from cart,
     * if it exist, decreases `selectedQuantity`,
     * else , removes it from the list
     * **/

    fun removeProduct(product: Product): CartItem {
        val index = cartActions.indexOfFirst { it.id == product.id }
        if (index != -1) {

            if (product.selectedQty > 0) {
                cartActions[index] = product
            } else {
                cartActions.removeAt(index)
            }
        }
        return CartItem(cartActions, computeTotalAmount())
    }

    /** Calculate total amount and total selected quantity
     * **/
    private fun computeTotalAmount(): Pair<Double, Int> {
        var totalAmount = 0.0
        var totalQuantity = 0

        cartActions.forEach {
            val totalSelectedQuantity = it.selectedQty
            val totalItemPrice = it.price ?: 0

            totalAmount += totalSelectedQuantity * totalItemPrice
            totalQuantity += totalSelectedQuantity
        }

        return Pair(totalAmount, totalQuantity)

    }

    fun clearCart(){
        cartActions.clear()
    }


}
