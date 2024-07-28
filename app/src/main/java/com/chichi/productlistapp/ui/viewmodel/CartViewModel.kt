package com.chichi.productlistapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.chichi.productlistapp.model.CartItem
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.util.CartManager
import com.chichi.productlistapp.util.ClickActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartManager: CartManager
) : ViewModel() {


    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val _cartEntity = MutableStateFlow(CartItem(mutableListOf(), Pair(0.00, 0)))
    val cartEntityList: StateFlow<CartItem> get() = _cartEntity

    val selectedProduct = MutableStateFlow<Product?>(null)

    private val _shouldRefresh = MutableStateFlow(false)
    val shouldRefresh: StateFlow<Boolean> get() = _shouldRefresh


    fun setProduct(clickActions: ClickActions, product: Product) {

        when (clickActions) {
            ClickActions.UPDATE -> {

                _cartEntity.value = cartManager.addProductToCart(product)

            }

            ClickActions.SET -> {
                _cartEntity.value = cartManager.insertProduct(product)
            }

            ClickActions.REMOVE -> {
                _cartEntity.value = cartManager.removeProduct(product)

            }

            else -> {}

        }
    }


    /**
     * gets single product from cartEntityList,
     * if cartEntityList Is Empty
     * get from products
     **/

    fun findProductById(id: Int): Product? {
        val entity = cartEntityList.value.products
        val full = products.value

        var product = entity.find { it.id == id }
        if (product == null) {
            product = full.find { it.id == id }
        }
        selectedProduct.value = product
        return product
    }


    fun buyNow() {
        resetCart()
    }


    /***
     * clears cart and refreshes data
     * */
    fun resetCart() {
        cartManager.clearCart()

        _cartEntity.update {
            it.copy(
                products = mutableListOf(),  total = Pair(0.00, 0)
            )
        }

        selectedProduct.value = null
        _shouldRefresh.value = true

    }

    fun resetRefresh(){
        _shouldRefresh.value = false
    }


    fun initializeCart(products: List<Product>) {
        _products.value = products
    }

}