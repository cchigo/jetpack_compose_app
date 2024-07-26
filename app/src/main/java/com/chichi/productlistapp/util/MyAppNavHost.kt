package com.chichi.productlistapp.util

import ProductScreen
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.Routes
import com.chichi.productlistapp.screens.home.HomeScreen
import com.chichi.productlistapp.ui.viewmodel.CartViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NavHost {
}

@OptIn(
    ExperimentalCoilApi::class, ExperimentalMaterial3Api::class, ExperimentalPagingApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: CartViewModel

) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = Routes.HomeScreen
    ) {


        composable<Routes.HomeScreen> {
            HomeScreen(
                onNavigateForward = { product ->
                    if (product != null) {
                        navController.navigate(Routes.ProductScreen(customPrimitive = Json.encodeToString(product)))
                    }

                }, cartViewModel = viewModel
            )
        }

        composable<Routes.ProductScreen>(

        ) {
                navBackStackEntry ->
            val parameters = navBackStackEntry.toRoute<Routes.ProductScreen>()

            ProductScreen(
                bundleString = parameters.customPrimitive.toString(),

                onNavigateBack = {
                    navController.navigate(route = Routes.HomeScreen)

                },
                cartViewModel = viewModel
            )
        }

    }
}