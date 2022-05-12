package com.example.curativepis.feature_ath.domian.use_case


class VaidteOTPCodeUseCase {
    operator fun invoke(otp: String): ValidatResult {
        if (otp.isBlank()) {
            return ValidatResult(
                isValid = false,
                errorMessage = "code can't be blank"
            )
        }
        //TODO : Edit it and remove hard code
        if (otp.length < 6) {
            return ValidatResult(
                isValid = false,
                errorMessage = "please enter correct code"
            )
        }
        return ValidatResult(
            isValid = true,
        )
    }
}