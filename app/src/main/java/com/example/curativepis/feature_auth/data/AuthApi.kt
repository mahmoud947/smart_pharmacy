package com.example.curativepis.feature_auth.data

import com.example.curativepis.feature_auth.data.remote.request.UserRequestObject
import com.example.curativepis.feature_auth.data.remote.response.PushUserResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("user/phone")
    suspend fun pushNewUser(
        @Header("Authorization") token:String,
        @Body userRequestObject: UserRequestObject
    ):PushUserResponse
}