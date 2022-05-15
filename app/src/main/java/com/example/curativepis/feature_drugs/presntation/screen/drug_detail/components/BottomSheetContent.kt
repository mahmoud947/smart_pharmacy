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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp


@Composable
fun BottomSheetContent(
    modifier: Modifier,
    painter: Painter,
) {

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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ArrowUpward,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onBackground)
                    }

                    Text(
                        text = "1",
                        style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onBackground)
                    )

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ArrowDownward,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onBackground)
                    }
                }

            }

            TitleAndValueText(title = "Drug name", value = "FJKJFKJDFKDJFK")

            IconButton(onClick = { /*TODO*/ }) {
                Column(
                    modifier = Modifier.background(MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Default.AddShoppingCart, contentDescription = null, tint = MaterialTheme.colors.onBackground)
                    Text(
                        text = "Add",
                        style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onBackground)
                    )
                }
            }

        }
    }

}