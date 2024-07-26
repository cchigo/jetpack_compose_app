package com.chichi.productlistapp.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.screens.cart.SingleProductView
import com.chichi.productlistapp.ui.viewmodel.CartViewModel
import com.chichi.productlistapp.ui.viewmodel.ProductListState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@ExperimentalCoilApi
@Composable
fun ProductBundleListScreen(
    onNavigateForward: ((Product?) -> Unit)? = null,
    screenState: ProductListState ?= null,
    cartViewModel: CartViewModel

) {
    if (screenState != null) {
        when {
            screenState.isLoading -> {
                LoadingIndicator()
            }

            screenState.error != null -> {
                ErrorScreen(error = screenState.error, onRetry = screenState.retry)
            }

            else -> {

                cartViewModel.initializeCart(screenState.products)



                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(screenState.products) { singleProduct ->

                        SingleProductView(
                            bundleList = screenState.products,
                            bundle = singleProduct,

                            onProductClick = {
                                onNavigateForward?.invoke(it)
                                Log.d("CLICK_TAG", "ProductBundleListScreen:${it.id} was clicked")
                            },
                            cartViewModel = cartViewModel

                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(error: String, onRetry: (() -> Unit)?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = error, color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        onRetry?.let {
            Button(onClick = it) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}



