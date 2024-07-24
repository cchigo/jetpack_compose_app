package com.chichi.productlistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chichi.productlistapp.util.Constants.PRODUCT_DATABASE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = PRODUCT_DATABASE)
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id: String,

)
