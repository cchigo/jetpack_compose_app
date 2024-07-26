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
    val quantity: Int? = 0,
    val status: String? = null,
    var selectedQty: Int = 0,

) : Parcelable {




    fun increaseSelectedQuantity() {}

    fun resetSelectedQuantity() {
        selectedQty = 0
    }

    fun decreaseQuantity() {
        if (selectedQty > quantity!!) selectedQty--
        else selectedQty = 0
    }

    fun updateQuantity(qty: Int) {
        selectedQty = if (qty > quantity!!)
           quantity
        else
            qty
    }
    val canIncreaseSelectedQty: Boolean
        get() = when {
            selectedQty < quantity!! -> true
            else -> false
        }



    val canDecreaseSelectedQty: Boolean
        get() = selectedQty > 1


//    var isExpanded by remember { mutableStateOf(bundle.selectedQty > 0) }
//    var quantityTextValue by rememberSaveable { mutableStateOf(bundle.selectedQty) }
//    var isMinusButtonEnabled by remember { mutableStateOf(bundle.selectedQty > 0) }
//    var isPlusButtonEnabled by remember { mutableStateOf(bundle.canIncreaseSelectedQty && ContentCaptureManager.isEnabled) }

}

//todo: make nullable
