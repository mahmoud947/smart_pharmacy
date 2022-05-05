package com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.ui.theme.spacing

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DrugSearchedItem(
    drug: Drug,
) {
    val painter = rememberImagePainter(data = drug.image)
    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = MaterialTheme.spacing.regulator,
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = drug.drug_name,
                    style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onSecondary.copy(0.8f), textAlign = TextAlign.Center),
                    modifier = Modifier.weight(4f),
                )
                Box(modifier = Modifier.size(40.dp).weight(1f)){
                    Image(painter = painter, contentDescription = null, modifier = Modifier.fillMaxSize())
                }
            }

        }

    }


}