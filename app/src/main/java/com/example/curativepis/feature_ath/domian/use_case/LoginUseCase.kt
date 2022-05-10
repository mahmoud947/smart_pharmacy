package com.example.curativepis.feature_ath.domian.use_case

data class LoginUseCase(
    val validPasswordUseCase: VaidatPasswordUseCase,
    val validUserNameUseCase: VaidteUserNameUseCase
)
