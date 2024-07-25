package com.chichi.productlistapp.data.remote.repository

import com.chichi.productlistapp.data.remote.BaseWrapper
import com.chichi.productlistapp.data.remote.RemoteDataSourceImpl
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl
) : Repository {


    override suspend fun getProducts(): BaseWrapper {
        return remoteDataSource.getProducts()
    }


}