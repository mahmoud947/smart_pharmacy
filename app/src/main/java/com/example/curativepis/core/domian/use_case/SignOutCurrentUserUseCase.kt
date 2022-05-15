package com.example.curativepis.core.domian.use_case

import com.google.firebase.auth.FirebaseAuth

class SignOutCurrentUserUseCase(
   private val firebaseAuth: FirebaseAuth
) {
    operator fun invoke()=firebaseAuth.signOut()
}