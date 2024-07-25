package com.chichi.productlistapp.navigation

import com.chichi.productlistapp.util.Constants.HOME_SCREEN_ROUTE
import com.chichi.productlistapp.util.Constants.PRODUCT_SCREEN_ROUTE

sealed class Screen(val route: String){
    object Home: Screen(HOME_SCREEN_ROUTE)
    object Product: Screen(PRODUCT_SCREEN_ROUTE)
}
