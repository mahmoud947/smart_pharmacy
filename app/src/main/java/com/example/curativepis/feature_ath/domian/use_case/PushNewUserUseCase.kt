package com.example.curativepis.feature_ath.domian.use_case
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_ath.data.remote.request.UserRequestObject
import com.example.curativepis.feature_ath.domian.repository.AuthRepository
import com.example.curativepis.feature_drugs.data.mapper.toDrug
import com.example.curativepis.feature_drugs.domian.model.Drug
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PushNewUserUseCase (
  private val repository: AuthRepository
){
    operator fun invoke(userRequestObject: UserRequestObject,toke:String): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading<Unit>(Unit))
            repository.pushNewUser(userRequestObject = userRequestObject, token = toke)
            emit(Resource.Success<Unit>(Unit))
        } catch (e: HttpException) {
            emit(Resource.Error<Unit>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Unit>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}
