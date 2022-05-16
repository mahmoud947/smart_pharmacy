package com.example.curativepis.feature_auth.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_auth.data.mapper.toCurrentUserResponse
import com.example.curativepis.feature_auth.data.remote.request.GetCustomTokenRequestObject
import com.example.curativepis.feature_auth.data.remote.response.CusttomTokenResponse
import com.example.curativepis.feature_auth.domian.model.CurrentUserResponse
import com.example.curativepis.feature_auth.domian.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCurrentUserFromServerSideUseCase(
    private val repository: AuthRepository,
) {
    operator fun invoke(token:String):Flow<Resource<CurrentUserResponse>> = flow {
        try {
            emit(Resource.Loading())
            val currentUserResponse = repository.getCurrentUser(token = token)?.toCurrentUserResponse()
            emit(Resource.Success<CurrentUserResponse>(data = currentUserResponse))
        } catch (e: HttpException) {
            emit(Resource.Error<CurrentUserResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CurrentUserResponse>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}