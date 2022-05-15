package com.example.curativepis.feature_auth.domian.use_case


class VaidatPasswordUseCase {
    operator fun invoke(password:String): ValidatResult {
        if (password.isBlank()){
            return ValidatResult(
                isValid = false,
                errorMessage = "password can't be blank"
            )
        }
        return ValidatResult(
            isValid = true,
        )
    }
}