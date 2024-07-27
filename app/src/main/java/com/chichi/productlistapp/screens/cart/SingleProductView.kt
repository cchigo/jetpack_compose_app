package com.chichi.productlistapp.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R
import coil.compose.rememberImagePainter
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.ui.viewmodel.CartViewModel
import com.chichi.productlistapp.util.CartManager


@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun SingleProductView(
    bundleList: List<Product>? = null,
    bundle: Product,
    onProductClick: ((Product) -> Unit)? = null,
    cartViewModel: CartViewModel
) {

    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .wrapContentHeight(),

        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column(
            Modifier
                .background(Color.White)
                .wrapContentHeight()
                .clickable {
                    if (onProductClick != null) {
                        onProductClick(bundle)
                    }
                }
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 1f)
            ) {
                val painter = rememberImagePainter(
                    data = bundle.imageLocation,
                    builder = {
                        placeholder(R.drawable.notification_template_icon_bg)
                        error(R.drawable.notification_template_icon_low_bg)
                        crossfade(1000)
                    }
                )
                Image(
                    painter = painter,
                    contentDescription = "Image",
                    modifier = Modifier.aspectRatio(12f / 6f),
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.TopEnd)
                        .background(Color.Red,  shape = RoundedCornerShape(
                            topStart = 8.dp,
                            bottomStart = 8.dp
                        )).padding(horizontal = 8.dp, vertical = 4.dp) // Add padding inside the background


                    ,
                    text = bundle.currencySymbol + bundle.price,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    )

                )
            }



            Text(
                modifier = Modifier.fillMaxWidth(),
                text = bundle.name ?: "",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = bundle.description ?: "",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                if (bundleList != null) {
                    AddToCartButtons(bundleList = bundleList , bundle = bundle, cartViewModel = cartViewModel )
                }
            }

        }


    }

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun PreviewSingleProductView() {
    val sampleProduct = Product(
        id = 1,
        currencyCode = "USD",
        currencySymbol = "$",
        description = "Sample product description",
        imageLocation = "http://example.com/image.png",
        name = "Sample Product",
        price = 100,
        quantity = 10,
        status = "Available",
        selectedQty = 1,
        selectedAmount = 100
    )


    SingleProductView(
        bundle = sampleProduct,
        bundleList = null,
        onProductClick = null,
        cartViewModel = CartViewModel(CartManager())
    )
}





