package com.chichi.productlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.chichi.productlistapp.screens.ProductBundleListScreen
import com.chichi.productlistapp.ui.home.HomeViewModel
import com.chichi.productlistapp.ui.theme.ProductListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalCoilApi::class)
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by homeViewModel.state.collectAsState()

            ProductListAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductBundleListScreen(
                        screenState = state
                    )
                }
            }
            }
        }

    }





