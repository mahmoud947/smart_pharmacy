package com.example.curativepis.feature_ath.data.remote.request

data class UserRequestObject(
    val deviceToken: String?,
    val dob: String,
    val isMale: Boolean,
    val phoneNumber: String,
    val uid: String,
    val username: String,
)