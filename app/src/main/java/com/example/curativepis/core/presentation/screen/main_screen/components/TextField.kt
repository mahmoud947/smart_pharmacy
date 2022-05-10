package com.example.curativepis.core.presentation.screen.main_screen.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable

@Composable
fun DefaultTextField(
    value:String,
    onTextChange:(String)->Unit={}

) {
    OutlinedTextField(value = value, onValueChange ={
       onTextChange(it)
    } )


}