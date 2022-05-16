package com.example.curativepis.feature_auth.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_auth.data.remote.request.GetCustomTokenRequestObject
import com.example.curativepis.feature_auth.data.remote.response.CusttomTokenResponse
import com.example.curativepis.feature_auth.domian.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCusttomTokentUseCase(
    private val repository: AuthRepository,
) {
    operator fun invoke(getCustomTokenRequestObject:GetCustomTokenRequestObject):Flow<Resource<CusttomTokenResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getCustomToken(getCustomTokenRequestObject = getCustomTokenRequestObject)
            emit(Resource.Success<CusttomTokenResponse>(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error<CusttomTokenResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CusttomTokenResponse>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}