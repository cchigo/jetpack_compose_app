package com.chichi.productlistapp.screens.home

import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    itemCount: Int, onIconClicked: () -> Unit // todo: display cart
) {
    TopAppBar(title = {
        Text(
            text = "Products", color = MaterialTheme.colorScheme.primary
        )
    },
        actions = {

            BadgeItem(itemCount)
        })
}

@Composable
@Preview
fun HomeTopBarPreview() {
   // HomeTopBar(itemCount = 200) {}
}
