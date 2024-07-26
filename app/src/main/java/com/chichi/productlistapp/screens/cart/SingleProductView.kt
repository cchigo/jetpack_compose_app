package com.chichi.productlistapp.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.R
import coil.compose.rememberImagePainter
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.ui.viewmodel.CartViewModel


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
//        Logo
//        Name
//        Price
//        Description
//        Add to Cart button
//                Buy Now button


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
            val painter = rememberImagePainter(data = bundle.imageLocation,

                builder = {
                    placeholder(R.drawable.notification_template_icon_bg)
                    error(R.drawable.notification_template_icon_low_bg)
                    crossfade(1000)
                })
            Image(
                painter = painter,
                contentDescription = "Image",
                modifier = Modifier.aspectRatio(12f / 6f),
                contentScale = ContentScale.Fit
            )
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
            Text(
                modifier = Modifier
                    .fillMaxWidth()  //Todo: format currency
                    .padding(2.dp), text = bundle.currencySymbol + bundle.price, style = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                ), maxLines = 1, overflow = TextOverflow.Ellipsis
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




