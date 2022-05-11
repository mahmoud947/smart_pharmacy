package com.example.curativepis.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.example.curativepis.R
import com.example.curativepis.ui.theme.spacing

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {

        CircularProgressIndicator(color = MaterialTheme.colors.primaryVariant)
    }
}

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
    message: String?,
    icon: Int = R.drawable.error_icon,
    textStyle: TextStyle =MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onBackground),
    iconColor: Color = MaterialTheme.colors.error,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
            if (message != null) {
                Text(text = message, style = textStyle)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))

            Button(onClick = { onClickRetry() }, modifier = Modifier.padding(bottom = MaterialTheme.spacing.regulator)) {
                Text(
                    text = "Retry",
                )
            }


        }
    }
}