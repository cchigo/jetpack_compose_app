package com.chichi.productlistapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chichi.productlistapp.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_database_tale")
    fun getAllImages(): PagingSource<Int, Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<Product>)

    @Query("DELETE FROM product_database_tale WHERE id = :productId")
    suspend fun deleteProductById(productId: Long)


}