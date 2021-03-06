package com.example.curativepis.feature_auth.presntation.screen.signup_screen

sealed class SignUpScreenEvent{
   object PassUserObject:SignUpScreenEvent()
    data class UsernameChanged(val username:String):SignUpScreenEvent()
    data class EmailChanged(val email:String):SignUpScreenEvent()
    data class PhoneChanged(val phone:String):SignUpScreenEvent()
    data class PasswordChanged(val password:String):SignUpScreenEvent()
    data class ConfirmPasswordChanged(val password:String,val confirmPassword:String,):SignUpScreenEvent()
    data class BirthDateChanged(val dateOfBirth:String):SignUpScreenEvent()
    data class GenderChanged(val isMale:Boolean):SignUpScreenEvent()
    data class OnNavigate(val root:String):SignUpScreenEvent()
    object SignUp:SignUpScreenEvent()
}
