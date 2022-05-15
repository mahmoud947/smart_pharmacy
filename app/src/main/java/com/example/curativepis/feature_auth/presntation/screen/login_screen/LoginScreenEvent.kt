package com.example.curativepis.feature_auth.presntation.screen.login_screen

sealed class LoginScreenEvent{
    data class UsernameChanged(val username:String):LoginScreenEvent()
    data class PasswordChanged(val password:String):LoginScreenEvent()
    object Login:LoginScreenEvent()
}
