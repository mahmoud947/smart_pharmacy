package com.example.curativepis.feature_ath.domian.use_case

import android.annotation.SuppressLint
import android.app.Activity
import android.content.SharedPreferences
import com.example.curativepis.MainActivity
import com.example.curativepis.feature_ath.domian.util.AuthConstants
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class SendOtpMessageUseCase(
    private val firebaseAuth: FirebaseAuth,
    private val sharedPreferences: SharedPreferences,
) {

    var verificationOtp=""
    operator fun invoke(mobileNum: String,activity: Activity) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+20$mobileNum")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object :
                PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(p0: FirebaseException) {

                }
                override fun onCodeSent(
                    otp: String,
                    p1: PhoneAuthProvider.ForceResendingToken,
                ) {
                    super.onCodeSent(otp, p1)
                   verificationOtp = otp
                }
            }).build()
        sharedPreferences.edit().remove(AuthConstants.SHERDPREFRENCES_VERIFICATIONOTP_KEY).apply()
        val rnds = (0..1000).random()
        sharedPreferences.edit()
            .putString(AuthConstants.SHERDPREFRENCES_VERIFICATIONOTP_KEY, verificationOtp).apply()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}