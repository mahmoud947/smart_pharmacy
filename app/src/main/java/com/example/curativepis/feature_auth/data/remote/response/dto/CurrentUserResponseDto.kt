package com.example.curativepis.feature_auth.data.remote.response.dto

data class CurrentUserResponseDto(
    val _id: String?,
    val createdAt: String?,
    val deviceToken: String?,
    val dob: String?,
    val email: String?,
    val isMale: Boolean?,
    val phoneNumber: String?,
    val role: String?,
    val uid: String?,
    val updatedAt: String?,
    val username: String?
)