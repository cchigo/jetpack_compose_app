package com.chichi.productlistapp.data.remote


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {



    override suspend fun getProducts(): BaseWrapper {
        return withContext(Dispatchers.IO) {
            try {
                BaseWrapper.Success(apiService.getProductsBundles())
            } catch (throwable: Throwable) {

                GeneralErrorHandlerImpl.getError<String>(throwable)
            }
        }
    }




}