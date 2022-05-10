package com.example.curativepis.feature_ath.domian.use_case

import androidx.compose.ui.res.stringResource
import com.example.curativepis.R


class VaidteUserNameUseCase {
    operator fun invoke(userName:String): ValidatResult {
        if (userName.isBlank()){
            return ValidatResult(
                isValid = false,
                errorMessage = "username can't be blank"
            )
        }
        return ValidatResult(
            isValid = true,
        )
    }
}