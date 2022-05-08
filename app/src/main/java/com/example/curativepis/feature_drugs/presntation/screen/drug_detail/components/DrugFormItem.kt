package com.example.curativepis.feature_drugs.presntation.screen.drug_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.R
import com.example.curativepis.feature_drugs.domian.model.DrugForm
import com.example.curativepis.ui.theme.spacing

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DrugFormItem(
    drugForm: DrugForm,
    modifier: Modifier = Modifier,
) {
    val painter = rememberImagePainter(data = drugForm.image, builder = {
        placeholder(R.drawable.loading_waiting)
        error(R.drawable.error_drug_image)
    })
    Card(
        modifier = modifier.fillMaxSize(),
        elevation = MaterialTheme.spacing.regulator,
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface,), verticalArrangement = Arrangement.Top) {
            Box(modifier = Modifier
                .padding(0.dp)
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(vertical = MaterialTheme.spacing.regulator)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = drugForm.form,
                    style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurface),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}