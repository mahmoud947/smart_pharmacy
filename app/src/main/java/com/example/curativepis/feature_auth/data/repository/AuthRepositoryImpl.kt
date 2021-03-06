package com.example.curativepis.feature_auth.data.repository

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_auth.data.AuthApi
import com.example.curativepis.feature_auth.data.remote.request.GetCustomTokenRequestObject
import com.example.curativepis.feature_auth.data.remote.request.UserRequestObject
import com.example.curativepis.feature_auth.data.remote.response.CusttomTokenResponse
import com.example.curativepis.feature_auth.data.remote.response.PushUserResponse
import com.example.curativepis.feature_auth.data.remote.response.dto.CurrentUserResponseDto
import com.example.curativepis.feature_auth.domian.repository.AuthRepository
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val api:AuthApi
) : AuthRepository {
    override fun isUserAuthenticatedInFirebase(): Boolean {
        return firebaseAuth.currentUser != null
    }


    override fun getFirebaseCurrentUserToken(): Resource<String> {
        return try {
            var token = ""
            if (firebaseAuth.currentUser != null) {
                firebaseAuth.currentUser?.getIdToken(true)?.addOnSuccessListener {
                    token = it.token.toString()
                }
                Resource.Success(data = token)
            }
            return Resource.Error(message = "user not found")
        } catch (e: IOException) {
            Resource.Error(message = e.localizedMessage ?: "check your internt connection")
        } catch (e: FirebaseException) {
            Resource.Error(message = e.localizedMessage ?: "an error occurred")
        }
    }


    override suspend fun pushNewUser(userRequestObject: UserRequestObject,token:String):PushUserResponse {
       return api.pushNewUser(userRequestObject = userRequestObject, token = token)
    }

    override suspend fun getCustomToken(getCustomTokenRequestObject: GetCustomTokenRequestObject): CusttomTokenResponse =
       api.getCustomToken(getCustomTokenRequestObject = getCustomTokenRequestObject)

    override suspend fun getCurrentUser(token: String): CurrentUserResponseDto? = api.getCurrentUser(token = token)


}