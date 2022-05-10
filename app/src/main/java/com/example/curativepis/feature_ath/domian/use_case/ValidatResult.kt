package com.example.curativepis.feature_ath.domian.use_case

data class ValidatResult(
    val isValid: Boolean,
    val errorMessage: String? = null,
)
