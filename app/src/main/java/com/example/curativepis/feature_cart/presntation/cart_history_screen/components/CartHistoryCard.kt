package com.example.curativepis.feature_cart.presntation.cart_history_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.R
import com.example.curativepis.feature_cart.domian.model.cart_history.CartHistory
import com.example.curativepis.feature_cart.domian.model.cart_history.CartHistoryItem
import com.example.curativepis.feature_cart.presntation.cart_screen.components.CartTitleAndValueText
import com.example.curativepis.ui.theme.elevation

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun CartHistoryCard(
    modifier: Modifier,
    cartHistoryItem: CartHistoryItem,
    cartHistory: CartHistory,
) {
    val imagePainter = rememberImagePainter(data = cartHistoryItem.image, builder = {
        placeholder(R.drawable.loading_waiting)
        error(R.drawable.error_drug_image)
    })
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = MaterialTheme.elevation.regulator,
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
                modifier = Modifier.weight(3f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                CartTitleAndValueText(title = "Drug name", value = cartHistoryItem.drug_name)
                CartTitleAndValueText(title = "Quantity",
                    value = cartHistoryItem.quantity.toString())
                CartTitleAndValueText(title = "Price",
                    value = cartHistoryItem.price.toString() + " L.E.")
                CartTitleAndValueText(title = "Total", value = cartHistoryItem.total.toString())
                CartTitleAndValueText(title = "CreatedAt", value = cartHistory.createdAt)
                if (cartHistory.updatedAt.isNotBlank())
                    CartTitleAndValueText(title = "UpdatedAt", value = cartHistory.updatedAt)
            }
        }
    }

}


