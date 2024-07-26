package com.chichi.productlistapp.ui.viewmodel

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

    private val _total = MutableStateFlow<Pair<Double, Int>>(Pair(0.0, 0))
    val total: StateFlow<Pair<Double, Int>> get() = _total


    fun setProduct(clickActions: ClickActions, product: Product) {
        when (clickActions) {
            ClickActions.UPDATE -> {
                _total.value= cartManager.addProductToCart(product)

                Log.d("TOTAL_TAG", "setProduct: ${_total.value}")
            }

            ClickActions.SET -> {
                _total.value = cartManager.insertProduct(product)
            }

            ClickActions.REMOVE -> {
                _total.value = cartManager.removeProduct(product)

            }

            else -> {}

        }
    }


    fun initializeCart(products: List<Product>) {
        _cartProducts.value = products
    }

    data class CartItem(val products: MutableList<Product>)
}