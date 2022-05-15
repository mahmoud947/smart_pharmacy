package com.example.curativepis.feature_auth.domian.use_case

data class ValidatResult(
    val isValid: Boolean,
    val errorMessage: String? = null,
)
