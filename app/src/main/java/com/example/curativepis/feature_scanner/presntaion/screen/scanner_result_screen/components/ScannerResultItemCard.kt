package com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.R
import com.example.curativepis.feature_cart.domian.model.cart.CartItem
import com.example.curativepis.feature_scanner.domian.model.ScannerResponse
import com.example.curativepis.ui.theme.elevation

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun ScannerResultItemCard(
    modifier: Modifier,
    scannerResponse: ScannerResponse,
    onClick: () -> Unit = {},
    onAddToCartIconClicked:()->Unit
) {
    val imagePainter = rememberImagePainter(data = scannerResponse.forms[0].image, builder = {
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
                ScannerTitleAndValueText(title = "Drug name", value = scannerResponse.drug_name)
                ScannerTitleAndValueText(title = "Strength", value = scannerResponse.strength)
                ScannerTitleAndValueText(title = "active_ingredients", value = scannerResponse.active_ingredients[0].toString())
                ScannerTitleAndValueText(title = "Price", value = scannerResponse.price.toString() + " L.E.")
            }
            IconButton(onClick = {
                onAddToCartIconClicked()
            }, modifier = Modifier.weight(1f)) {
                Icon(imageVector = Icons.Default.AddShoppingCart, contentDescription = null, tint = MaterialTheme.colors.onSurface)
            }
        }
    }

}


