package com.chichi.productlistapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chichi.productlistapp.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AddToCartButtons(
    onMinusClick: () -> Unit, onPlusClick: () -> Unit, isEnabled: Boolean = true
) {
    var isExpanded by remember { mutableStateOf(false) }
    var quantityTextValue by rememberSaveable { mutableStateOf("1") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(48.dp)
            .clickable(onClick = { isExpanded = !isExpanded })
            .background(Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(4.dp), color = Color.Green
                )
                .align(Alignment.Center)

        ) {
            if (isExpanded) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    IconButton(
                        onClick = onMinusClick,
                        enabled = isEnabled,
                        modifier = Modifier
                            .background(Color.Blue, shape = RoundedCornerShape(4.dp))
                            .weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = "Minus Icon",
                            tint = Color.White
                        )
                    }

                    BasicTextField(
                        maxLines = 1,
                        value = quantityTextValue,
                        onValueChange = {
                            quantityTextValue = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        singleLine = true,
                        modifier = Modifier.background(
                            shape = RoundedCornerShape(4.dp), color = Color.Green
                        )

                    )


                    IconButton(
                        onClick = onPlusClick,
                        enabled = isEnabled,
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.Blue, shape = RoundedCornerShape(4.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_dark_24),
                            contentDescription = "Add Icon",
                            tint = Color.White
                        )
                    }
                }
            } else {
                Text(

                    text = "Add to Cart",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }
        }

    }
}

//todo: add element, shared transition

