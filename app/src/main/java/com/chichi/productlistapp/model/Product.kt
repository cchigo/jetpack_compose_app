package com.chichi.productlistapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chichi.productlistapp.util.Constants.PRODUCT_DATABASE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = PRODUCT_DATABASE)
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val currencyCode: String? = null,
    val currencySymbol: String? = null,
    val description: String? = null,
    val imageLocation: String? = null,
    val name: String? = null,
    val price: Int? = null,
    val quantity: Int? = null,
    val status: String? = null

) : Parcelable {

    var available: Boolean = true
    var selectedQty: Int = 0


    fun increaseSelectedQuantity() {}

    fun resetSelectedQuantity() {
        selectedQty = 0
    }

    fun decreaseQuantity() {
//        if (selectedQty > quantity) selectedQty--
//        else selectedQty = 0
    }

    fun updateQuantity(qty: Int) {

    }


}

//todo: make nullable
