package com.chichi.productlistapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.chichi.productlistapp.ClickActions
import com.chichi.productlistapp.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
):ViewModel(){



    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> get() = _product

    private val _products = MutableStateFlow<List<Product>>(listOf())
    val products: StateFlow<List<Product>> get() = _products

    private val updatedProducts = MutableStateFlow<List<Product>>(listOf())


    fun addProduct(cartAction:ClickActions, product: Product){
        Log.d("PTAG", "addProduct: $product")
        Log.d("PTAG2", "addProduct: ${product.selectedQty}")
        when(cartAction){
            ClickActions.UPDATE -> {
                // Add the product to the updatedProducts list
                updatedProducts.value = updatedProducts.value.toMutableList().apply {
                    add(product)
                }
            }
            ClickActions.REMOVE -> {
                // Remove the product from the updatedProducts list
                updatedProducts.value = updatedProducts.value.toMutableList().apply {
                    remove(product)
                }
            }
            else -> {
                // Handle any other actions if needed
            }
        }

    }

    fun decreaseProduct(){

    }
}