package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.curativepis.ui.theme.spacing
import androidx.compose.ui.graphics.Color

@Composable
fun TextBetweenDivider(
    modifier: Modifier= Modifier,
    textStyle: TextStyle=MaterialTheme.typography.subtitle1,
    text:String,
    color:Color=MaterialTheme.colors.primaryVariant
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Divider(modifier = Modifier.width(MaterialTheme.spacing.defaultDivider), color = color)
        Text(text =text, style = textStyle.copy(color = color) , modifier = Modifier.padding(MaterialTheme.spacing.regulator))
        Divider(modifier = Modifier.width(MaterialTheme.spacing.defaultDivider), color = color)
    }

}