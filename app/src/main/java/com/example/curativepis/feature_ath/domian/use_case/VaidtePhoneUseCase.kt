package com.example.curativepis.feature_ath.domian.use_case

import android.util.Patterns


class VaidtePhoneUseCase {
    operator fun invoke(phone:String): ValidatResult {
        if (phone.isBlank()){
            return ValidatResult(
                isValid = false,
                errorMessage = "phone can't be blank"
            )
        }
        //TODO : Edit it and remove hard code
        if (phone.length<10){
            return ValidatResult(
                isValid = false,
                errorMessage = "phone length must be > 10"
            )
        }
        return ValidatResult(
            isValid = true,
        )
    }
}