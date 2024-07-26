package com.chichi.productlistapp.util

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.chichi.productlistapp.model.Product
import javax.inject.Inject

class CartManager @Inject constructor() {

    private val cartActions = mutableStateListOf<Product>()

    /** Adds product to cart, if it exist, updates `selectedQuantity`**/
    fun addProductToCart(product: Product): SnapshotStateList<Product> {
        val index = cartActions.indexOfFirst { it.id == product.id }
        if (index != -1) {
            cartActions[index] = cartActions[index].copy(selectedQty = product.selectedQty)
        } else {
            cartActions.add(product.copy(selectedQty = product.selectedQty))
        }
        return cartActions
    }

    /** Inserts product to cart, and, updates `selectedQuantity`**/
    fun insertProduct(product: Product): SnapshotStateList<Product> {
        cartActions.add(product.copy(selectedQty = product.selectedQty))
        return cartActions
    }

    /** Removes product from cart,
     * if it exist, decreases `selectedQuantity`,
     * else , removes it from the list
     * **/

    fun removeProduct(product: Product): SnapshotStateList<Product> {
        val index = cartActions.indexOfFirst { it.id == product.id }
        if (index != -1) {
            val currentProduct = cartActions[index]
            val updatedProduct = currentProduct.copy(selectedQty = currentProduct.selectedQty - 1)
            if (updatedProduct.selectedQty > 0) {
                cartActions[index] = updatedProduct
            } else {
                cartActions.removeAt(index)
            }
        }
        return cartActions
    }


}
