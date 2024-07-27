package com.chichi.productlistapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chichi.productlistapp.data.remote.BaseWrapper
import com.chichi.productlistapp.data.remote.get
import com.chichi.productlistapp.data.remote.repository.RepositoryImpl
import com.chichi.productlistapp.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel() {
    private val _state = MutableStateFlow(ProductListState())
    val state: StateFlow<ProductListState> = _state.asStateFlow()

    private var job: Job? = null

    init {
        getProducts()
    }



     fun getProducts() {
        _state.update { it.copy(isLoading = true) }

        job?.cancel()
        job = viewModelScope.launch {
            when (val res = repository.getProducts()) {
                is BaseWrapper.Success<*> -> {
                    val result = res.get<List<Product>>()
                    _state.update {
                        it.copy(
                            products = result ?: emptyList(), isLoading = false, error = null
                        )
                    }
                }

                is BaseWrapper.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = res.message,
                            retry = { getProducts() }
                        )
                    }
                }

                else -> {
                    _state.update { it.copy(isLoading = false, retry = { getProducts() }) }
                }
            }
        }
    }

}


data class ProductListState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val retry: (() -> Unit)? = null //todo: Add retry flow, when an error occurs
)


