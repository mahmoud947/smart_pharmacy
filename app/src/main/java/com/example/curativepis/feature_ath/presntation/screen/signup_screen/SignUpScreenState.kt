package com.example.curativepis.feature_ath.presntation.screen.signup_screen

data class SignUpScreenState(
    val email:String="",
    val emailErrorMessage:String?=null,
    val phone:String="",
    val phoneErrorMessage:String?=null,
    val password:String="",
    val passwordErrorMessage:String?=null,
    val confirmPassword:String="",
    val confirmPasswordErrorMessage:String?=null,
    val dateOfBirth:String="20/5/2022",
    val dateOfBirthErrorMessage:String?=null,
    val isMale:Boolean=true,
    val isLoading:Boolean=false
)
