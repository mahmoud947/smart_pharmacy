package com.example.curativepis.feature_auth.data.remote.response

data class PushUserResponse(
    val _id: String?,
    val createdAt: String?,
    val deviceToken: String?,
    val dob: String?,
    val isMale: Boolean?,
    val is_error: Boolean?,
    val message: String?,
    val phoneNumber: String?,
    val role: String?,
    val uid: String?,
    val updatedAt: String?,
    val username: String?
)