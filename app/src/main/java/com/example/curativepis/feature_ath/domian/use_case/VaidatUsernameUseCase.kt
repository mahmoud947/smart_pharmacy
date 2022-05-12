package com.example.curativepis.feature_ath.domian.use_case

import android.util.Patterns
import java.util.regex.Pattern


class VaidatUsernameUseCase {
    operator fun invoke(username:String): ValidatResult {
        if (username.isBlank()){
            return ValidatResult(
                isValid = false,
                errorMessage = "username can't be blank"
            )
        }
//        if (username.length<10) {
//            return ValidatResult(
//                isValid = false,
//                errorMessage = "username to short"
//            )
//        }
            if (!isLowerCase(username)){
                return ValidatResult(
                    isValid = false,
                    errorMessage = "username must be in lower case"
                )
        }
        return ValidatResult(
            isValid = true,
        )
    }

    private fun isLowerCase(string: String):Boolean{
        var isLower=true
    for (i in string)
        isLower=i.isLowerCase()

        return isLower
    }
}