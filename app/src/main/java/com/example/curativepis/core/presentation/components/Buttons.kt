package com.example.curativepis.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.curativepis.ui.theme.appShape
import com.example.curativepis.ui.theme.spacing


@Composable
fun ButtonWithElevation(
    modifier: Modifier = Modifier,
    defaultElevation: Dp = 4.dp,
    pressedElevation: Dp = 8.dp,
    startIcon: Painter?=null,
    endIcon: Painter?=null,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colors.primaryVariant,
    shape: Shape = MaterialTheme.appShape.large,
    text: String,
    textStyle: TextStyle= MaterialTheme.typography.button,
    isEnable:Boolean=true
    ) {

    Button(onClick = {
        onClick()
    }, elevation = ButtonDefaults.elevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        disabledElevation = 0.dp
    ),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colors.onPrimary,
            backgroundColor = color
        ),
        shape = shape,
        modifier = modifier,
        enabled = isEnable
    ) {
        if (startIcon != null) {
           Icon(painter = startIcon, contentDescription = null, tint = MaterialTheme.colors.onPrimary, modifier = Modifier.size(24.dp).padding(end = MaterialTheme.spacing.small))
        }
        Text(text = text, color = MaterialTheme.colors.onPrimary, style = textStyle)
        if (endIcon != null) {
            Icon(painter = endIcon, contentDescription = null, tint = MaterialTheme.colors.onPrimary, modifier = Modifier.size(24.dp).padding(start = MaterialTheme.spacing.small))
        }
    }
}