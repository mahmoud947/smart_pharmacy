package com.example.curativepis.feature_auth.domian.use_case

import com.google.firebase.auth.FirebaseAuth

class GetFirebaseCurrentUser(
   private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke()=firebaseAuth.currentUser
}