package com.example.curativepis.feature_ath.domian.repository

import com.example.curativepis.core.util.network.Resource
import kotlinx.coroutines.flow.Flow



interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
    fun getFirebaseCurrentUserToken(): Resource<String>
}