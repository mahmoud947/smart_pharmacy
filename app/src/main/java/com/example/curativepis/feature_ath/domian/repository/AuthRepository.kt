package com.example.curativepis.feature_ath.domian.repository

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_ath.data.remote.request.UserRequestObject
import com.example.curativepis.feature_ath.data.remote.response.CreateUserResponse
import kotlinx.coroutines.flow.Flow



interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
    fun getFirebaseCurrentUserToken(): Resource<String>
   suspend fun pushNewUser(userRequestObject: UserRequestObject,token:String):Resource<Unit>
}