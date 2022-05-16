package com.example.curativepis.feature_auth.presntation.screen.login_screen

data class LoginScreenState(
    val email:String="",
    val emailErrorMessage:String?=null,
    val password:String="",
    val passwordErrorMessage:String?=null,
    val usernameOrPasswordError:String?=null,
    val isLoading:Boolean=false,
    val isEmailOrPasswordError:Boolean=false
)
