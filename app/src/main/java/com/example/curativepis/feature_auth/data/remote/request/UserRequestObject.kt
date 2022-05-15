package com.example.curativepis.feature_auth.data.remote.request

data class UserRequestObject(
    val username: String,
    val phoneNumber: String,
    val deviceToken: String?,
    val uid: String,
    val isMale: Boolean,
    val dob: String,
    val password:String
)