package com.chichi.productlistapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi.productlistapp.data.remote.BaseWrapper
import com.chichi.productlistapp.data.remote.get
import com.chichi.productlistapp.data.remote.repository.RepositoryImpl
import com.chichi.productlistapp.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {
    private val _products: MutableStateFlow<BaseWrapper> = MutableStateFlow(BaseWrapper.Empty)
    val products = _products.asStateFlow()

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow = _loadingFlow.asStateFlow()

    private var job: Job? = null


    fun getProducts() {
        Log.d("PTAG", "getProducts: here")
        _products.value = BaseWrapper.Loading
        _loadingFlow.value = true

        job?.cancel()
        job = viewModelScope.launch {

            try {
                when(val res = repository.getProducts()){
                    is BaseWrapper.Success<*> ->{
                        Log.d("PTAG", "getProducts succ: here")
                        _loadingFlow.value = false
                        val result = res.get<List<Product>>()
                        _products.value = BaseWrapper.Success(result)
                    }
                    is BaseWrapper.Error -> {
                        _loadingFlow.value = false
                        _errorFlow.emit(res.message)
                    }
                    else -> {}
                }
            }catch (e: Exception){
                Log.d("PTAG", "error: $e")
            }



        }
    }
}
