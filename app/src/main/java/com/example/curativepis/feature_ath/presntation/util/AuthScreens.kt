package com.example.curativepis.feature_ath.presntation.util

import com.example.curativepis.feature_drugs.presntation.util.DrugScreenArguments

sealed class AuthScreens(val route:String){
    object LoginScreen:AuthScreens(route = "login_screen")
    object SpalshScreen:AuthScreens(route = "splash_screen")
    object SignUpScreen:AuthScreens(route = "signup_screen")
    object OTPScreen:AuthScreens(route = "otp_screen/{${AuthScreenArguments.User_DETAIL_SCREEN_ARGUMENT_KEY}}"){
        fun passUserDetails(userDetails:String):String{
            return this.route.replace(oldValue ="{${AuthScreenArguments.User_DETAIL_SCREEN_ARGUMENT_KEY}}", newValue = userDetails )
        }
    }
}