package com.chichi.productlistapp.data.remote.repository

import com.chichi.productlistapp.data.remote.BaseWrapper

interface Repository {

    suspend fun getProducts(): BaseWrapper

}