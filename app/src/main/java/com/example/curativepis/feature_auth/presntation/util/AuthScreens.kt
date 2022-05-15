package com.example.curativepis.feature_auth.presntation.util

sealed class AuthScreens(val route: String) {
    object LoginScreen : AuthScreens(route = "login_screen")
    object SpalshScreen : AuthScreens(route = "splash_screen")
    object SignUpScreen : AuthScreens(route = "signup_screen")
    object OTPScreen :
        AuthScreens(route = "otp_screen" +
                "/{${AuthScreenArguments.OTP_SCREEN_ARGUMENT_PHONE_KEY}}" +
                "/{${AuthScreenArguments.OTP_SCREEN_ARGUMENT_EMAIL_KEY}}" +
                "/{${AuthScreenArguments.OTP_SCREEN_ARGUMENT_ISMALE_KEY}}" +
                "/{${AuthScreenArguments.OTP_SCREEN_ARGUMENT_USERNAME_KEY}}" +
                "/{${AuthScreenArguments.OTP_SCREEN_ARGUMENT_PASSWORD_KEY}}" +
                "/{${AuthScreenArguments.OTP_SCREEN_ARGUMENT_DTOD_KEY}}") {
        fun passUserDetails(
            phone: String,
            email: String,
            isMale: Boolean,
            userName: String,
            password: String,
            dto: String,
        ): String {
            return "otp_screen"+"/$phone/$email/$isMale/$userName/$password/$dto"
        }
    }
}