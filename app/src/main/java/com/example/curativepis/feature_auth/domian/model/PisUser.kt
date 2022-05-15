package com.example.curativepis.feature_auth.domian.model

data class PisUser(
    val dob: String,
    val email:String,
    val isMale: Boolean,
    val phoneNumber: String,
    val username: String,
    val password:String
)