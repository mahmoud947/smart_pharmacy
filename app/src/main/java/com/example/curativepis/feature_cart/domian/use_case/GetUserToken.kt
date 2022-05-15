package com.example.curativepis.feature_cart.domian.use_case

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult

class GetUserToken(
    private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke(): Task<GetTokenResult>? {
      return  firebaseAuth.currentUser?.getIdToken(true)
    }
}