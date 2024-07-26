package com.chichi.productlistapp.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.ui.viewmodel.CartViewModel
import com.chichi.productlistapp.ui.viewmodel.HomeViewModel


@ExperimentalMaterial3Api
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    onNavigateForward: ((Product?) -> Unit)? = null,
    homeViewModel: HomeViewModel = hiltViewModel(),
    cartViewModel: CartViewModel
) {

    val state by homeViewModel.state.collectAsState()
    val cartState by cartViewModel.total.collectAsState()


    Scaffold(topBar = {
        HomeTopBar( itemCount = cartState)

    }, content = { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ProductBundleListScreen(screenState = state, onNavigateForward = onNavigateForward, cartViewModel = cartViewModel)

        }
    }
    )
}