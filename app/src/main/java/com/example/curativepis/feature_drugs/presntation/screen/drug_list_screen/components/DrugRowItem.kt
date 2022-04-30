package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.curativepis.feature_drugs.domain.model.Drug

@Composable
fun DrugRowItem(
    modifier: Modifier = Modifier,
    drugs: List<Drug>?
) {
    Row(modifier = modifier.fillMaxSize()) {
        if (drugs != null) {
            for(drug in drugs){

            }
        }

    }
}