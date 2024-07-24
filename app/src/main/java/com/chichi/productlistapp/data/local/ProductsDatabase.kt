package com.chichi.productlistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chichi.productlistapp.data.local.dao.ProductDao
import com.chichi.productlistapp.model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}