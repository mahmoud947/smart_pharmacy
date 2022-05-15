package com.example.curativepis.feature_cart.presntation.cart_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.R
import com.example.curativepis.feature_cart.domian.model.CartItem
import com.example.curativepis.ui.theme.elevation

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun CartItemCard(
    modifier: Modifier,
    cartItem: CartItem,
    onClick: () -> Unit = {},
    onDleteIconClicked:()->Unit
) {
    val imagePainter = rememberImagePainter(data = cartItem.image, builder = {
        placeholder(R.drawable.loading_waiting)
        error(R.drawable.error_drug_image)
    })
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = MaterialTheme.elevation.regulator,
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .height(100.dp)
                .weight(1f)) {
                Image(painter = imagePainter, contentDescription = null)
            }
            Column(
                modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                CartTitleAndValueText(title = "Drug name", value = cartItem.drug_name)
                CartTitleAndValueText(title = "Quantity", value = cartItem.quantity.toString())
                CartTitleAndValueText(title = "Price", value = cartItem.price.toString() + " L.E.")
                CartTitleAndValueText(title = "Total", value = cartItem.drug_name)
            }
            IconButton(onClick = {
                onDleteIconClicked()

            }, modifier = Modifier.weight(1f)) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = MaterialTheme.colors.onSurface)
            }
        }

    }

}


