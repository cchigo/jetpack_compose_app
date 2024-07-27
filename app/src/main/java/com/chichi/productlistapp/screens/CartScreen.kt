package com.chichi.productlistapp.screens

import androidx.compose.runtime.Composable
import com.chichi.productlistapp.model.Product

@Composable
fun CartScreen(
    items: List<Product>,
    onUpdate: (Product) -> Unit,
    onRemove: (Product) -> Unit
) {
//    Scaffold(
//        topBar = {
//            HomeTopBar(
//                itemCount = items.sumOf { it.selectedQty },
//                onIconClicked = { /* Handle cart icon click */ }
//            )
//        }
//    ) { paddingValues ->
//        LazyColumn(
//            contentPadding = paddingValues,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            items(items) { product ->
//                CartItemView(
//                    product = product,
//                    onUpdate = { updatedProduct -> onUpdate(updatedProduct) },
//                    onRemove = { removedProduct -> onRemove(removedProduct) }
//                )
//            }
//        }
//    }
}
