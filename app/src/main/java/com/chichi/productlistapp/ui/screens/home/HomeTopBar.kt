package com.chichi.productlistapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(

    itemCount: Int,
    showLeftButton: Boolean = false,
    leftButton: (() -> Unit)? = null
) {

    Log.d("ITEM_TAG", "HomeTopBar: $itemCount")
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                toggleLeftIcon(showLeftButton, leftButton)
                Spacer(modifier = Modifier.weight(1f))
                Text("Bundle List")
                Spacer(modifier = Modifier.weight(1f))
                BadgeItem(count = itemCount)
            }
        },
        actions = {
        })
}



@Composable
fun toggleLeftIcon(showLeftButton: Boolean, leftButton: (() -> Unit)?) {
    if (showLeftButton) {
        if (leftButton != null) {
            IconButton(onClick = leftButton) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Menu")
            }
           
        }

    }
}
