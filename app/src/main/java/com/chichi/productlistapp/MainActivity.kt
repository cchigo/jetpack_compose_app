package com.chichi.productlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.navigation.NavGraph
import com.chichi.productlistapp.ui.theme.ProductListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalCoilApi::class)
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ProductListAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)

            }
        }
    }

}





