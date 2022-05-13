package com.example.curativepis.feature_ath.presntation.screen.otp_screen


data class OTPScreenState(
    val otpCode:String="",
    val otpCodeErrorMessage:String?=null,
    val isLoading:Boolean=false,
)
