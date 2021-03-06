package com.example.curativepis.feature_auth.domian.use_case


class VaidtePhoneUseCase {
    operator fun invoke(phone:String): ValidatResult {
        if (phone.isBlank()){
            return ValidatResult(
                isValid = false,
                errorMessage = "phone can't be blank"
            )
        }
        //TODO : Edit it and remove hard code
        if (phone.length!=11){
            return ValidatResult(
                isValid = false,
                errorMessage = "phone number is incorrect"
            )
        }
        return ValidatResult(
            isValid = true,
        )
    }
}