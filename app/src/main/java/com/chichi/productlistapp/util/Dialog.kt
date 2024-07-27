package com.chichi.productlistapp.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


@Composable
fun MyButtonWithDialog(
    onDismissDialog: (() -> Unit)?,
    dialogTitle: String,
    dialogText: String
) {
    val showDialog = remember { mutableStateOf(true) }


    if (showDialog.value) {
        ShowDialog(
            onDismiss = {
                showDialog.value = false
                onDismissDialog?.invoke()
            },
            text = dialogText,
            title = dialogTitle
        )
    }
}



@Composable
fun ShowDialog(
    onDismiss: () -> Unit,
    text: String,
    title: String
) {
    var showProgress by remember { mutableStateOf(true) }
    var showContent by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(5000)
        showProgress = false
        showContent = true
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { if (showContent) Text(title) else Text ("Please wait")},
        text = { if (showContent) Text(text) else Text("Processing ...")},
        confirmButton = {
            if (showContent) {
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            } else {
                Box(
                    modifier = Modifier.width(IntrinsicSize.Max)
                        .height(IntrinsicSize.Max), // Use intrinsic size
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    )
}

