package com.example.curativepis.feature_auth.domian.repository

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_auth.data.remote.request.GetCustomTokenRequestObject
import com.example.curativepis.feature_auth.data.remote.request.UserRequestObject
import com.example.curativepis.feature_auth.data.remote.response.CusttomTokenResponse
import com.example.curativepis.feature_auth.data.remote.response.PushUserResponse


interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
    fun getFirebaseCurrentUserToken(): Resource<String>
    suspend fun pushNewUser(userRequestObject: UserRequestObject,token:String):PushUserResponse
    suspend fun getCustomToken(getCustomTokenRequestObject: GetCustomTokenRequestObject):CusttomTokenResponse
}