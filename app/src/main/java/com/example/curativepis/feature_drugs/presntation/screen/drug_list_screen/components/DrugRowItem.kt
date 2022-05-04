package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.ui.theme.spacing

@Composable
fun DrugRowItem(
    modifier: Modifier = Modifier,
    rowIndex: Int,
    drugs: List<Drug>?,
) {
    Column {
        Row(modifier = modifier.fillMaxSize()) {
            if (drugs != null) {

                DrugsCardItem(
                    drugImageUrl = drugs[rowIndex * 3].image,
                    drugName = drugs[rowIndex * 3].drug_name,
                    modifier = Modifier.weight(1f)
                ) {}
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                if (drugs.size >= rowIndex * 3 + 3) {
                    DrugsCardItem(
                        drugImageUrl = drugs[rowIndex * 3 + 1].image,
                        drugName = drugs[rowIndex * 3 + 1].drug_name,
                        modifier = Modifier.weight(1f)
                    ) {}
                    DrugsCardItem(
                        drugImageUrl = drugs[rowIndex * 3 + 2].image,
                        drugName = drugs[rowIndex * 3 + 2].drug_name,
                        modifier = Modifier.weight(1f)
                    ) {}
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                }else{
                    Spacer(modifier = Modifier.weight(2f))
                }

            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    }
}