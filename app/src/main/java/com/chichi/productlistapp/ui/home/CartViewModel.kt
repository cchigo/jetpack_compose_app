package com.chichi.productlistapp.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.util.CartManager
import com.chichi.productlistapp.util.ClickActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartManager: CartManager
) : ViewModel() {


    private val _cartProducts = MutableStateFlow<List<Product>>(emptyList())
    val cartProducts: StateFlow<List<Product>> get() = _cartProducts


    var cartDetails = mutableStateListOf<Product>() // reacts to changes in cart actions


    fun setProduct(clickActions: ClickActions, product: Product) {
        when (clickActions) {
            ClickActions.UPDATE -> {
                cartDetails = cartManager.addProductToCart(product)
            }
            ClickActions.SET -> {
                cartDetails = cartManager.insertProduct(product)
            }
            ClickActions.REMOVE -> {
                cartDetails = cartManager.removeProduct(product)

            }

            else -> {}

        }
    }


    fun initializeCart(products: List<Product>) {

        products.forEach {
            Log.d("LIST_TAG", "initializeCart: ${it.id} >> ${it.selectedQty}")
        }
        _cartProducts.value = products
    }
}