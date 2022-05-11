package com.example.curativepis.feature_ath.presntation.screen.signup_screen

sealed class SignUpScreenEvent{
    data class EmailChanged(val email:String):SignUpScreenEvent()
    data class PhoneChanged(val phone:String):SignUpScreenEvent()
    data class PasswordChanged(val password:String):SignUpScreenEvent()
    data class ConfirmPasswordChanged(val password:String,val confirmPassword:String,):SignUpScreenEvent()
    data class BirthDateChanged(val dateOfBirth:String):SignUpScreenEvent()
    data class GenderChanged(val isMale:Boolean):SignUpScreenEvent()
    object SignUp:SignUpScreenEvent()
}
