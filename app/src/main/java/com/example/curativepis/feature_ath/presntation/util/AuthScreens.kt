package com.example.curativepis.feature_ath.presntation.util

sealed class AuthScreens(val route:String){
    object LoginScreen:AuthScreens(route = "login_screen")
    object SpalshScreen:AuthScreens(route = "splash_screen")
    object SignUpScreen:AuthScreens(route = "signup_screen")
    object OTPScreen:AuthScreens(route = "otp_screen/{${AuthScreenArguments.OTP_SCREEN_ARGUMENT_PHONE_KEY}}"){
        fun passPhone(phone:String):String{
            return this.route.replace(oldValue ="{${AuthScreenArguments.OTP_SCREEN_ARGUMENT_PHONE_KEY}}", newValue = phone )
        }
    }
}