package com.example.curativepis.feature_auth.domian.model

data class CurrentUserResponse(
    val _id: String,
    val createdAt: String,
    val deviceToken: String,
    val dob: String,
    val email: String,
    val isMale: Boolean,
    val phoneNumber: String,
    val role: String,
    val uid: String,
    val updatedAt: String,
    val username: String
)