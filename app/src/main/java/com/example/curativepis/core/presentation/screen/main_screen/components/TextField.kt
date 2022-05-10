package com.example.curativepis.core.presentation.screen.main_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.example.curativepis.ui.theme.spacing

@Composable
fun DefaultTextField(
    value: String,
    label: String,
    onTextChange: (String) -> Unit = {},
    pading:PaddingValues= PaddingValues(horizontal = MaterialTheme.spacing.medium),
    keyboardOptions:KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email
    ),
    isError:Boolean=false

    ) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth()
            .padding(pading)
        ,
        value = value, onValueChange = {
            onTextChange(it)
        }, label = { Text(text = label, style = MaterialTheme.typography.caption) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            focusedLabelColor = MaterialTheme.colors.primaryVariant,
            unfocusedLabelColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.6f),
            unfocusedBorderColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.6f),
        ),
        isError = isError
    )


}