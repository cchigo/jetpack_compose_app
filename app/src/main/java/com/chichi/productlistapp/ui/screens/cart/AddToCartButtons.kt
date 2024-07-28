package com.chichi.productlistapp.ui.screens.cart

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chichi.productlistapp.R
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.ui.viewmodel.CartViewModel
import com.chichi.productlistapp.util.CartActions.onMinusClicked
import com.chichi.productlistapp.util.CartActions.onPlusClicked
import com.chichi.productlistapp.util.CartActions.onQuantityChanged
import com.chichi.productlistapp.util.CartActions.shouldExpand
import com.chichi.productlistapp.util.CartManager
import com.chichi.productlistapp.util.ClickActions

@Composable
fun AddToCartButtons(
    bundleList: List<Product>,
    bundle: Product,
    isEnabled: Boolean = true,
    cartViewModel: CartViewModel

) {
    var quantityTextValue by remember { mutableStateOf(bundle.selectedQty.toString()) }
    var amountTextValue by remember { mutableStateOf(bundle.selectedAmount.toString()) }
    var showError by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(bundle.selectedQty > 0) }

    LaunchedEffect(quantityTextValue) {
        isExpanded = shouldExpand(quantityTextValue)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .clickable(onClick = {

                isExpanded = !isExpanded
                if (isExpanded && quantityTextValue.toInt() < 1) {
                    val value = onPlusClicked(bundle,
                        { quantityTextValue = it },
                        { amountTextValue = it },
                        { showError = it })
                    cartViewModel.setProduct(clickActions = ClickActions.SET, product = value)

                }
            })

    ) {
        Column(


            horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
            verticalArrangement = Arrangement.Center // Center items vertically
        ) {
            Text(
                text = if (amountTextValue.toInt() > 0) bundle.currencySymbol+ amountTextValue else "", // Replace with amountTextValue if needed
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color.White) // Set background color
                    .padding(4.dp) // Optional, adds padding inside the Text
                , style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                )
            )

            Box(



            ) {
                if (isExpanded) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Minus Icon
                        IconButton(
                            onClick = {
                                onMinusClicked(bundle = bundle,
                                    setQuantityTextValue = { quantityTextValue = it },
                                    setShowError = { showError = it },
                                    setAmountTextValue = { amountTextValue = it })


                                cartViewModel.setProduct(
                                    clickActions = ClickActions.REMOVE, product = bundle
                                )

                            }, enabled = isEnabled, modifier = Modifier
                                .background(
                                    Color.Blue, shape = RoundedCornerShape(4.dp)
                                )
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
                            onValueChange = { newValue ->
                                onQuantityChanged(newValue = newValue,
                                    bundle = bundle,
                                    setQuantityTextValue = { quantityTextValue = it },
                                    setAmountTextValue = { amountTextValue = it },
                                    setShowError = { showError = it })

                            },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                            singleLine = true,
                            enabled = !showError,
                            modifier = Modifier
                                .background(
                                    shape = RoundedCornerShape(4.dp),
                                    color = if (showError) Color.Gray.copy(alpha = 0.5f) else Color.White.copy(
                                        alpha = 0.8f
                                    )
                                )
                                .padding(8.dp)
                                .weight(1f)
                        )

                        // Plus Icon
                        IconButton(
                            onClick = {
                                val pluss = onPlusClicked(bundle,
                                    { quantityTextValue = it },
                                    { amountTextValue = it },
                                    { showError = it })


                                cartViewModel.setProduct(
                                    clickActions = ClickActions.UPDATE, product = bundle
                                )

                                Log.d("PLUS_TAG", "AddToCartButtons: $pluss")
                                //cartViewModel.addProductToCart(clickActions = ClickActions.UPDATE, product = bundle)
                            },
                            enabled = isEnabled && !showError,
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
                            .background(Color.Yellow)
                            .padding(8.dp)
                    )
                }
            }

        }


    }
}

@Preview
@Composable
fun showPreview() {
    AddToCartButtons(
        emptyList(), Product(
            0, "", "", "", "", "", 0, 0, "", 0, 0
        ), cartViewModel = CartViewModel(CartManager())

    )
}


//todo: add element, shared transition

