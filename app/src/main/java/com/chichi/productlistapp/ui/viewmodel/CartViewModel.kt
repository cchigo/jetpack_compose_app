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


    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val _cartEntity = MutableStateFlow(CartItem())
    val cartEntityList: StateFlow<CartItem> get() = _cartEntity

    val selectedProduct = MutableStateFlow<Product?>(null)

    private val _shouldRefresh =  MutableStateFlow(false)
    val shouldRefresh: StateFlow<Boolean> get() = _shouldRefresh

    fun setProduct(clickActions: ClickActions, product: Product) {
        when (clickActions) {
            ClickActions.UPDATE -> {

                //  _total.value = cartManager.addProductToCart(product).total
                _cartEntity.value = cartManager.addProductToCart(product)

            }

            ClickActions.SET -> {
                //    _total.value = cartManager.insertProduct(product).total
                _cartEntity.value = cartManager.insertProduct(product)
            }

            ClickActions.REMOVE -> {
                //    _total.value = cartManager.removeProduct(product).total
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
    fun findProductById_(id: Int){
        val entity = cartEntityList.value.products
        val full = products.value

        //if entity is empty, or size is less than 1, use full for the computation below, please retain the names

        val products = entity ?: full
        val ppp = products.find { it.id == id }

        Log.d("ENTITYTAG", "findProductById: ${entity?.size} // ${full.size} >>> $id, $ppp")


        selectedProduct.value = ppp


    }

    fun findProductById(id: Int): Product? {
        val entity = cartEntityList.value.products
        val full = products.value

        val productsToSearch = entity.ifEmpty { full }
        val ppp = productsToSearch.find { it.id == id }

        Log.d("ENTITYTAG", "findProductById: ${entity.size} // ${full.size} >>> $id, $ppp")

        selectedProduct.value = ppp
        return ppp
    }

    fun buyNow(){
       resetCart()
    }


    /***
     * clears cart and refreshes data
     * */
     fun resetCart(){

        _cartEntity.value = _cartEntity.value.copy(
            products = mutableListOf(),
            total = Pair(0.00, 0)
        )
        _products.value = emptyList()
       selectedProduct.value = null
        _shouldRefresh.value = true
    }


    fun initializeCart(products: List<Product>) {
        _products.value = products
    }





    data class CartItem(
        val products: MutableList<Product> = mutableListOf(), // Default values
        val total: Pair<Double, Int> = Pair(0.00, 0)
    )
}