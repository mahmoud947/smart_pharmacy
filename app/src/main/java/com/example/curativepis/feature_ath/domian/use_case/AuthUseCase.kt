package com.example.curativepis.feature_ath.domian.use_case

data class AuthUseCase(
    val validPasswordUseCase: VaidatPasswordUseCase,
    val validEmailUseCase: VaidteEmailUseCase,
    val validPhoneUseCase: VaidtePhoneUseCase,
    val validConfirmPasswordUseCase: VaidatConfirmPasswordUseCase,
)
