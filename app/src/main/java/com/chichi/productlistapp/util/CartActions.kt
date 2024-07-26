package com.chichi.productlistapp.util

import com.chichi.productlistapp.model.Product

object CartActions {

    fun onQuantityChanged(
        newValue: String,
        bundle: Product,
        setQuantityTextValue: (String) -> Unit,
        setShowError: (Boolean) -> Unit
    ) {
        try {
            val enteredQty = newValue.toInt()
            if (enteredQty > 0 && enteredQty <= bundle.quantity!!) {
                setQuantityTextValue(newValue)
                bundle.updateQuantity(enteredQty)
                setShowError(false)
            } else {
                setShowError(true)
            }
        } catch (e: NumberFormatException) {
            setQuantityTextValue(bundle.selectedQty.toString())
        }
    }

    fun onPlusClicked(
        bundle: Product,
        setQuantityTextValue: (String) -> Unit,
        setShowError: (Boolean) -> Unit
    ) {
        if (bundle.selectedQty < bundle.quantity!!) {
            bundle.selectedQty += 1
            setQuantityTextValue(bundle.selectedQty.toString())
            setShowError(false)
        } else {
            setShowError(true)
        }
    }

    fun onMinusClicked(
        bundle: Product,
        setQuantityTextValue: (String) -> Unit,
        setShowError: (Boolean) -> Unit
    ) {
        if (bundle.selectedQty > 0) {
            bundle.selectedQty -= 1
            setQuantityTextValue(bundle.selectedQty.toString())
            setShowError(false)
        }
    }

    fun shouldExpand(quantity: String): Boolean {
        val num = quantity.toIntOrNull()
        return num != null && num > 0
    }
}

enum class ClickActions { REMOVE, UPDATE, NONE }