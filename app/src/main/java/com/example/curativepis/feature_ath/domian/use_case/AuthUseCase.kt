package com.example.curativepis.feature_ath.domian.use_case

data class AuthUseCase(
    val validUsernameUseCase: VaidatUsernameUseCase,
    val validPasswordUseCase: VaidatPasswordUseCase,
    val validEmailUseCase: VaidteEmailUseCase,
    val validPhoneUseCase: VaidtePhoneUseCase,
    val validConfirmPasswordUseCase: VaidatConfirmPasswordUseCase,
    val validOTPCodeUseCase: VaidteOTPCodeUseCase,
    val sendOtpMessageUseCase: SendOtpMessageUseCase,
    val verificationOtpUseCase: VerificationOtpUseCase,
    val pushNewUserUseCase: PushNewUserUseCase
)
