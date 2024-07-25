package com.chichi.productlistapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.ui.home.ProductListState

@ExperimentalCoilApi
@Composable
fun ProductBundleListScreen(
    screenState: ProductListState
) {
    when {
        screenState.isLoading -> {
            LoadingIndicator()
        }
        screenState.error != null -> {
            ErrorScreen(error = screenState.error, onRetry = screenState.retry)
        }
        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(all = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = screenState.products,
                    key = { product -> product.id }
                ) { singleProduct ->
                    SingleProductView(singleProduct)
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


