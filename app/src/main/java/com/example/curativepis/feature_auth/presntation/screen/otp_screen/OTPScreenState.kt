package com.example.curativepis.feature_auth.presntation.screen.otp_screen


data class OTPScreenState(
    val otpCode:String="",
    val otpCodeErrorMessage:String?=null,
    val isLoading:Boolean=false,
    val createUserIsError:Boolean=false,
    val createUserIsErrorMessage: String?=null
)
