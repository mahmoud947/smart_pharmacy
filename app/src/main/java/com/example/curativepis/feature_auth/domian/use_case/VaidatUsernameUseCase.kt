package com.example.curativepis.feature_auth.domian.use_case


class VaidatUsernameUseCase {
    operator fun invoke(username:String): ValidatResult {
        if (username.isBlank()){
            return ValidatResult(
                isValid = false,
                errorMessage = "username can't be blank"
            )
        }
        if (username.length<7){
            return ValidatResult(
                isValid = false,
                errorMessage = "username must be at least 8 characters"
            )
        }
        val containsLettersInLowerCaseAndDigits = username.any { it.isDigit() } &&
                username.any { it.isLetter()&&isLowerCase(it) }

            if (!containsLettersInLowerCaseAndDigits){
                return ValidatResult(
                    isValid = false,
                    errorMessage = "The username needs to contain letter in lower case and at least one digit"
                )
        }
        return ValidatResult(
            isValid = true,
        )
    }

    private fun isLowerCase(char: Char):Boolean{
        return char.isLowerCase()
    }
}