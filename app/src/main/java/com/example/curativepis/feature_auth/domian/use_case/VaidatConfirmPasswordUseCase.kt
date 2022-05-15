package com.example.curativepis.feature_auth.domian.use_case


class VaidatConfirmPasswordUseCase {
    operator fun invoke(password:String,confirmPassword:String): ValidatResult {
        if (password!=confirmPassword){
            return ValidatResult(
                isValid = false,
                errorMessage = "The passwords don't match"
            )
        }
        return ValidatResult(
            isValid = true,
        )
    }
}