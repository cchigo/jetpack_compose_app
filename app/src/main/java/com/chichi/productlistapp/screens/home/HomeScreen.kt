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
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.screens.ProductBundleListScreen
import com.chichi.productlistapp.ui.home.HomeViewModel


@ExperimentalMaterial3Api
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()
) {

    val state by homeViewModel.state.collectAsState()

    Scaffold(topBar = {
        HomeTopBar(onIconClicked = {

        }, itemCount = 5)

    }, content = { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ProductBundleListScreen(screenState = state)
        }
    })
}