
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R
import coil.compose.rememberImagePainter
import com.chichi.productlistapp.model.Product
import com.chichi.productlistapp.screens.cart.AddToCartButtons
import com.chichi.productlistapp.screens.home.HomeTopBar
import com.chichi.productlistapp.ui.viewmodel.CartViewModel
import com.chichi.productlistapp.ui.viewmodel.CartViewModel.CartItem
import com.chichi.productlistapp.util.CartManager
import com.chichi.productlistapp.util.MyButtonWithDialog
import kotlinx.serialization.json.Json

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun ProductScreen(
    onNavigateBack: (() -> Unit)? = null,
    bundleString: String,
    cartViewModel: CartViewModel
) {

    val cartState by cartViewModel.cartEntityList.collectAsState()
    val productList = cartViewModel.products.collectAsState()
    val updatedProductById = cartViewModel.selectedProduct.collectAsState()

   Scaffold(topBar = {
        HomeTopBar(
            showLeftButton = true,
            itemCount = cartState.total.second,
            leftButton = {
                onNavigateBack?.invoke()
            }
        )
    }, content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp), // No spacing between items
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(padding)

                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    Modifier
                        .background(Color.White)
                        .wrapContentHeight()
                ) {
                    val painter = rememberImagePainter(
                        data = updatedProductById.value?.imageLocation,
                        builder = {
                            placeholder(R.drawable.notification_template_icon_bg)
                            error(R.drawable.notification_template_icon_bg)
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
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        text = updatedProductById.value?.name ?: "",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        text = updatedProductById.value?.description ?: "",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .padding(2.dp),
                        text = updatedProductById.value?.currencySymbol + updatedProductById.value?.price,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )


                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                    ) {
                        if (productList != null) {
                            updatedProductById.value?.let {
                                AddToCartButtons(
                                    bundleList = productList.value,
                                    bundle = it,
                                    cartViewModel = cartViewModel
                                )
                            }
                        }
                    }
                }

                MyButtonWithDialogScreen(
                    cartState = cartState,
                    cartViewModel = cartViewModel,
                    onNavigateBack = { onNavigateBack?.invoke() })
            }


        }


    })
}


@Composable
fun MyButtonWithDialogScreen(
    cartState: CartItem,
    onNavigateBack: (() -> Unit)? = null,
    cartViewModel: CartViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Button(
        onClick = {
            if (cartState.products.isEmpty()) {
                Toast.makeText(context, "Please add to cart to proceed", Toast.LENGTH_SHORT).show()
            } else {
                showDialog = true
            }
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.Green
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 4.dp)
            .alpha(if (showDialog) 0.5f else 1.0f)
            .background(color = if (showDialog) Color.DarkGray else Color.Red),
        enabled = !showDialog
    ) {
        // Empty content block
        Text(text = "Buy now")
    }

    if(showDialog) {

        MyButtonWithDialog(
            onDismissDialog = {
                cartViewModel.buyNow()
                onNavigateBack?.invoke()
            },
            dialogTitle = "Transaction successful!",
            dialogText = "Continue shopping."
        )
    }
}





