package com.chichi.productlistapp.data.remote

import com.chichi.productlistapp.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("productBundles")
    suspend fun getProductsBundles(): List<Product>


}