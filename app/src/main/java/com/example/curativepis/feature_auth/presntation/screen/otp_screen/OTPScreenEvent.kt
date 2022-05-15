package com.example.curativepis.feature_auth.presntation.screen.otp_screen

import android.app.Activity
import com.example.curativepis.feature_auth.data.remote.request.UserRequestObject

sealed class OTPScreenEvent{
    data class OtpCodeChanged(val code:String):OTPScreenEvent()
    data class VirifyCode(val code: String,val activity: Activity):OTPScreenEvent()
    data class GetUserData(val userAsJson:String):OTPScreenEvent()
    data class SendOtpMessage(val phone: String,val activity: Activity): OTPScreenEvent()
    data class SignUp(val userRequestObject: UserRequestObject,val token:String):OTPScreenEvent()
}
