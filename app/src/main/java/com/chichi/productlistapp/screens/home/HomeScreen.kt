package com.chichi.productlistapp.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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


    val shouldRefresh =  cartViewModel.shouldRefresh.collectAsState()

    if (shouldRefresh.value ) {
        homeViewModel.getProducts()
        cartViewModel.resetRefresh()
    }

    val state by homeViewModel.state.collectAsState()
    val  cartState = cartViewModel.cartEntityList.collectAsState()

    Scaffold(topBar = {
        HomeTopBar( itemCount = cartState.value.total.second)


    }, content = { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ProductBundleListScreen(screenState = state, onNavigateForward = onNavigateForward, cartViewModel = cartViewModel)

        }
    }
    )
}