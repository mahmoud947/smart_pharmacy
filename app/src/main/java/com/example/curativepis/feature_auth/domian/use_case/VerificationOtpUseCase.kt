package com.example.curativepis.feature_auth.domian.use_case

import android.app.Activity
import android.content.SharedPreferences
import android.util.Log
import com.example.curativepis.feature_auth.domian.util.AuthConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

class VerificationOtpUseCase (
    val firebaseAuth: FirebaseAuth,
   val sharedPreferences: SharedPreferences,
) {
    private val verificationOtp=sharedPreferences.getString(AuthConstants.SHERDPREFRENCES_VERIFICATIONOTP_KEY,"")?:""
    private var isVerifiy=false
    operator fun invoke(otp:String,activity:Activity):Boolean{
        Log.d("auth",verificationOtp)
        val credential = PhoneAuthProvider.getCredential(verificationOtp, otp)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->

                isVerifiy = task.isSuccessful
            }
        return isVerifiy
    }
}