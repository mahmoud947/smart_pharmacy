package com.example.curativepis.feature_ath.data

import com.example.curativepis.feature_ath.data.remote.request.UserRequestObject
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("user/phone")
    suspend fun pushNewUser(
        @Header("Authorization") token:String,
        @Body userRequestObject: UserRequestObject
    ):Any
}