package com.example.curativepis.feature_auth.presntation.screen.otp_screen.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun OtpTextField(
    value: String,
    onTextChange: (String) -> Unit,
    onFill: (String) -> Unit,
    isError: Boolean = false,
    modifier:Modifier=Modifier
) {


    OutlinedTextField(
        modifier=modifier,
        value = value,
        label = {
                Text(text = "CODE", style = MaterialTheme.typography.subtitle1)
        },
        onValueChange = {

            if (it.length <= 6) {
                onTextChange(it)
            }
            if (it.length == 6) {
                onFill(it)
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            focusedLabelColor = MaterialTheme.colors.primaryVariant,
            unfocusedLabelColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.6f),
            unfocusedBorderColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.6f),
        ),
        maxLines = 1,
        isError = isError,

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        textStyle = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.primaryVariant)
    )


}