package com.chichi.productlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.chichi.productlistapp.ui.theme.ProductListAppTheme
import com.chichi.productlistapp.ui.viewmodel.CartViewModel
import com.chichi.productlistapp.ui.viewmodel.HomeViewModel
import com.chichi.productlistapp.util.MyAppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val cartViewModel by viewModels<CartViewModel>()
    private val homeViewModel by viewModels<HomeViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            ProductListAppTheme {
                val navController = rememberNavController()
                MyAppNavHost(
                    navController = navController, viewModel = cartViewModel, homeViewModel = homeViewModel
                )
            }
        }
    }

}




@kotlinx.serialization.Serializable
sealed class Routes {
    @kotlinx.serialization.Serializable
    data object HomeScreen : Routes()

    @kotlinx.serialization.Serializable
    data class ProductScreen(val customPrimitive: String) : Routes()

}






