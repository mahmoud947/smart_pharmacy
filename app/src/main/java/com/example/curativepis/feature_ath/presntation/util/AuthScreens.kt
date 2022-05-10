package com.example.curativepis.feature_ath.presntation.util

sealed class AuthScreens(val rout:String){
    object LoginScreen:AuthScreens(rout = "login_screen")
}