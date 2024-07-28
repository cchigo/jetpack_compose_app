package com.chichi.productlistapp.util

import com.chichi.productlistapp.model.Product

object CartActions {

    fun onQuantityChanged(
        newValue: String,
        bundle: Product,
        setQuantityTextValue: (String) -> Unit,
        setAmountTextValue: (String) -> Unit,
        setShowError: (Boolean) -> Unit
    ) {
        try {
            val enteredQty = newValue.toInt()
            if (enteredQty > 0 && enteredQty <= bundle.quantity!!) {
                setQuantityTextValue(newValue)

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
        setAmountTextValue: (String) -> Unit,
        setShowError: (Boolean) -> Unit
    ): Product {
        if (bundle.selectedQty < bundle.quantity!!) {
            bundle.selectedQty += 1
            setQuantityTextValue(bundle.selectedQty.toString())

            val amount = (bundle.price ?: 0) * bundle.selectedQty
            bundle.selectedAmount = amount
            setAmountTextValue(bundle.selectedAmount.toString())
            setShowError(false)
        } else {
            setShowError(true)
        }
        return bundle
    }

    fun onMinusClicked(
        bundle: Product,
        setQuantityTextValue: (String) -> Unit,
        setShowError: (Boolean) -> Unit,
        setAmountTextValue: (String) -> Unit,
    ) {
        if (bundle.selectedQty > 0) {
            bundle.selectedQty -= 1
            setQuantityTextValue(bundle.selectedQty.toString())
            val amount = (bundle.price ?: 0) * bundle.selectedQty
            bundle.selectedAmount = amount
            setAmountTextValue(amount.toString())

            setShowError(false)
        }
    }

    fun shouldExpand(quantity: String): Boolean {
        val num = quantity.toIntOrNull()
        return num != null && num > 0
    }
}

enum class ClickActions { REMOVE, UPDATE, NONE, SET }