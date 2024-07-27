package com.chichi.productlistapp.screens

import androidx.compose.runtime.Composable
import com.chichi.productlistapp.model.Product

@Composable
fun CartItemView(
    product: Product,
    onUpdate: (Product) -> Unit,
    onRemove: (Product) -> Unit
) {
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth(),
//        elevation = 4.dp
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = product.name ?: "Unnamed Product",
//                style = MaterialTheme.typography.h6
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                AddToCartButtons(
//                    quantity = product.selectedQty,
//                    onQuantityChange = { newQuantity ->
//                        onUpdate(product.copy(selectedQty = newQuantity))
//                    }
//                )
//                IconButton(
//                    onClick = { onRemove(product) },
//                    modifier = Modifier
//                        .background(Color.Red, shape = RoundedCornerShape(4.dp))
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_remove),
//                        contentDescription = "Remove Icon",
//                        tint = Color.White
//                    )
//                }
//            }
//        }
//    }
}
