package com.example.curativepis.feature_ath.presntation.util

sealed class AuthScreens(val route:String){
    object LoginScreen:AuthScreens(route = "login_screen")
    object SpalshScreen:AuthScreens(route = "splash_screen")
    object SignUpScreen:AuthScreens(route = "signup_screen")
}