package com.example.curativepis.feature_auth.domian.use_case

import android.util.Patterns


class VaidteEmailUseCase {
    operator fun invoke(email:String): ValidatResult {
        if (email.isBlank()){
            return ValidatResult(
                isValid = false,
                errorMessage = "email can't be blank"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()){
            return ValidatResult(
                isValid = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidatResult(
            isValid = true,
        )
    }
}