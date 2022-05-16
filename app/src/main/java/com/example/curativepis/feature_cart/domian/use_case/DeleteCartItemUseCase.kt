package com.example.curativepis.feature_cart.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_cart.data.remote.response.CartBaseResponse
import com.example.curativepis.feature_cart.domian.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DeleteCartItemUseCase(
    private val repository: CartRepository
) {
    operator fun invoke(token:String,deleteCartItemId:String): Flow<Resource<CartBaseResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.deleteItemFromCart(token = token, deleteCartItemId =deleteCartItemId)
            emit(Resource.Success<CartBaseResponse>(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error<CartBaseResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CartBaseResponse>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}