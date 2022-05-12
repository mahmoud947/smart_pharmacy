package com.example.curativepis.feature_ath.presntation.screen.otp_screen

sealed class OTPScreenEvent{
    data class OtpCodeChanged(val code:String):OTPScreenEvent()
    object VirifyCode:OTPScreenEvent()
    data class GetUserData(val userAsJson:String):OTPScreenEvent()
}
