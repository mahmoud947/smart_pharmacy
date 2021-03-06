package com.example.curativepis.feature_auth.domian.use_case
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_auth.data.remote.request.UserRequestObject
import com.example.curativepis.feature_auth.data.remote.response.PushUserResponse
import com.example.curativepis.feature_auth.domian.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PushNewUserUseCase (
  private val repository: AuthRepository
){
    operator fun invoke(userRequestObject: UserRequestObject,toke:String): Flow<Resource<PushUserResponse>> = flow {
        try {
            emit(Resource.Loading<PushUserResponse>())
            val response=repository.pushNewUser(userRequestObject = userRequestObject, token = toke)
            emit(Resource.Success<PushUserResponse>(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error<PushUserResponse>(message = e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<PushUserResponse>(message = e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}
