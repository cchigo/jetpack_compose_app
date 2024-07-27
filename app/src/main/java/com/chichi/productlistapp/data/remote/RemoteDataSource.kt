package com.chichi.productlistapp.data.remote

interface RemoteDataSource {

    suspend fun getProducts(): BaseWrapper



}