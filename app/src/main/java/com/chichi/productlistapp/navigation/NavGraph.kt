package com.chichi.productlistapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
//        composable(route = Screen.Product.route){
//            ProductScreen(navController = navController)
//        }
    }
}