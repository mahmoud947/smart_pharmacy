package com.example.curativepis.feature_ath.presntation.screen.login_screen

data class LoginScreenState(
    val username:String="",
    val usernameErrorMessage:String?=null,
    val password:String="",
    val passwordErrorMessage:String?=null,
    val usernameOrPasswordError:String?=null,
    val isLoading:Boolean=false
)
