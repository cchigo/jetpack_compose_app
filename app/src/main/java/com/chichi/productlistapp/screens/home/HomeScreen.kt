package com.chichi.productlistapp.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.ui.viewmodel.CartViewModel
import com.chichi.productlistapp.ui.viewmodel.HomeViewModel


@ExperimentalMaterial3Api
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    onNavigateForward: ((Product?) -> Unit)? = null,
    homeViewModel: HomeViewModel ,
    cartViewModel: CartViewModel
) {

    val state by homeViewModel.state.collectAsState()
    val cartState by cartViewModel.cartEntityList.collectAsState()
    val shouldRefresh =  cartViewModel.shouldRefresh.collectAsState()

    var hasFetchedProducts by remember { mutableStateOf(false) }

    if (shouldRefresh.value && !hasFetchedProducts) {
        homeViewModel.getProducts()
        hasFetchedProducts = true

    }


    Scaffold(topBar = {
        HomeTopBar( itemCount = cartState.total.second)


    }, content = { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ProductBundleListScreen(screenState = state, onNavigateForward = onNavigateForward, cartViewModel = cartViewModel)

        }
    }
    )
}