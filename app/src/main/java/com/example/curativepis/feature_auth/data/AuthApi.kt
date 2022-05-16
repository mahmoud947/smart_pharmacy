package com.example.curativepis.feature_auth.data

import com.example.curativepis.feature_auth.data.remote.request.GetCustomTokenRequestObject
import com.example.curativepis.feature_auth.data.remote.request.UserRequestObject
import com.example.curativepis.feature_auth.data.remote.response.CusttomTokenResponse
import com.example.curativepis.feature_auth.data.remote.response.PushUserResponse
import com.example.curativepis.feature_auth.data.remote.response.dto.CurrentUserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("user/phone")
    suspend fun pushNewUser(
        @Header("Authorization") token:String,
        @Body userRequestObject: UserRequestObject
    ):PushUserResponse

    @POST("user/token")
    suspend fun getCustomToken(
        @Body getCustomTokenRequestObject: GetCustomTokenRequestObject
    ):CusttomTokenResponse

    @GET("user")
    suspend fun getCurrentUser(
        @Header("Authorization") token:String,
    ):CurrentUserResponseDto?

}