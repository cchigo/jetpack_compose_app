package com.chichi.productlistapp.data.remote

import com.chichi.productlistapp.data.remote.NetworkHelper.GET_PRODUCT_BUNDLE
import com.chichi.productlistapp.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET(GET_PRODUCT_BUNDLE)
    suspend fun getProductsBundles(): List<Product>


}