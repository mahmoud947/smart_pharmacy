package com.example.curativepis.core.presentation.screen.main_screen.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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

        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
    message: String?,
    icon: Int = R.drawable.error_icon,
    textStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colors.error
    ),
    iconColor:Color= MaterialTheme.colors.error
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
            Icon(painter = painterResource(id = icon), contentDescription = null)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
            if (message != null) {
                Text(text =message, style = textStyle)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing. regulator))
            Box(modifier = Modifier.clickable { onClickRetry() }){
                Text(
                    text = "Retry",
                    style = TextStyle(color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.ExtraBold)
                )
            }
            

        }
    }
}