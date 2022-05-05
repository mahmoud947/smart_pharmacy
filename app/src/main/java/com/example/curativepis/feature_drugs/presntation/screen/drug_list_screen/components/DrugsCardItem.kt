package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.R
import com.example.curativepis.ui.theme.spacing


@OptIn(ExperimentalCoilApi::class)
@Composable
fun DrugsCardItem(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    cardElevation: Dp = 8.dp,
    drugImageUrl: String,
    drugName: String,
    drugPrice:String,
    onClick: () -> Unit,

    ) {
    val imagePainter = rememberImagePainter(data = drugImageUrl, builder = {
        placeholder(R.drawable.loading_waiting)
        error(R.drawable.error_drug_image)
    })

    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
            .clickable { onClick() },
        elevation = cardElevation,
        shape = shape,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = drugName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .padding(vertical = MaterialTheme.spacing.regulator)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier=Modifier.padding(MaterialTheme.spacing.small),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = drugName,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSecondary,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = "$drugPrice EL",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSecondary,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                }

            }

        }

    }

}
