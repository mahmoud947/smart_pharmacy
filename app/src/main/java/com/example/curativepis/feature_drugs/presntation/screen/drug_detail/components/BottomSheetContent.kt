package com.example.curativepis.feature_drugs.presntation.screen.drug_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.curativepis.ui.theme.spacing


@Composable
fun BottomSheetContent(
    modifier: Modifier,
    painter: Painter,
    onAddToCartClicked: (Int) -> Unit,
    drugName: String,
    drugPrice: Double,
) {

    val itemCount = remember {
        mutableStateOf(1)
    }

    val total = remember {
        mutableStateOf(drugPrice * itemCount.value)
    }
    Surface(
        contentColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(modifier = modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .height(100.dp)
                    .weight(1f, fill = false)
                    .clip(shape = MaterialTheme.shapes.medium)) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .height(150.dp)
                        .weight(1f, fill = false),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = {
                        if (itemCount.value < 10) {
                            itemCount.value = itemCount.value + 1
                            total.value = drugPrice * itemCount.value
                        }
                    }) {
                        Icon(imageVector = Icons.Default.ArrowUpward,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onBackground)
                    }

                    Text(
                        text = "${itemCount.value}",
                        style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onBackground)
                    )

                    IconButton(onClick = {
                        if (itemCount.value > 1) {
                            itemCount.value = itemCount.value - 1
                            total.value =drugPrice * itemCount.value

                        }
                    }) {
                        Icon(imageVector = Icons.Default.ArrowDownward,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onBackground)
                    }
                }

            }

            TitleAndValueText(title = "Drug name", value = drugName)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
            TitleAndValueText(title = "Total", value = String.format("%.2f",total.value))

            IconButton(onClick = { onAddToCartClicked(itemCount.value) }) {
                Column(
                    modifier = Modifier.background(MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Default.AddShoppingCart,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground)
                    Text(
                        text = "Add",
                        style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onBackground)
                    )
                }
            }

        }
    }

}