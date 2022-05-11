package com.example.curativepis.feature_ath.presntation.screen.signup_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.curativepis.ui.theme.spacing

@Composable
fun GenderSection(
    isMale: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val misMale = remember { mutableStateOf(true) }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Gender",
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        RadioButton(selected = misMale.value, onClick = {
            misMale.value = !misMale.value
            isMale(misMale.value)
        }, colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colors.primaryVariant
        ))
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = "Male",
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        RadioButton(selected = !misMale.value, onClick = {
            misMale.value = !misMale.value
            isMale(misMale.value)
        }, colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colors.primaryVariant
        ))
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = "Female",
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
        )
    }
}
