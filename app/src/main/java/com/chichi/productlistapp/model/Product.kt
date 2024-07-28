package com.chichi.productlistapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chichi.productlistapp.util.Constants.PRODUCT_DATABASE
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


//api model
@Parcelize
@Serializable
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
    var selectedQty: Int = 0, // added to hold selected quantity state
    var selectedAmount: Int = 0, // added to hold amount state

    ) : Parcelable

/**
 * Added `selectedQty` and `selectedAmount` to hold the values of selected quantity and amount.
 * TODO: Create a generator to convert the API product model to another model that holds the above values.
 */


