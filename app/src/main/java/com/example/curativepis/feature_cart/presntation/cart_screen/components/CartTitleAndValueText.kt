package com.example.curativepis.feature_cart.presntation.cart_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.curativepis.ui.theme.spacing

@Composable
fun CartTitleAndValueText(
    modifier: Modifier=Modifier,
    title: String,
    value: String?,
    isCenter:Boolean = false,
) {
    Row(modifier = modifier.fillMaxWidth()
        .padding(start = MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if(isCenter)Arrangement.Center else Arrangement.Start) {
        Text(text = "$title: ", style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onBackground))
        if (value != null) {
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(text = value, style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground))
        }

    }

}