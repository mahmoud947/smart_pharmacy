package com.example.curativepis.feature_cart.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_cart.data.mapper.toCart
import com.example.curativepis.feature_cart.data.mapper.toCartHistory
import com.example.curativepis.feature_cart.domian.model.cart.Cart
import com.example.curativepis.feature_cart.domian.model.cart_history.CartHistory
import com.example.curativepis.feature_cart.domian.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCarHistorytUseCase(
    private val repository: CartRepository,
) {
    operator fun invoke(token: String):Flow<Resource<List<CartHistory>>> = flow {
        try {
            emit(Resource.Loading())
            val cartHistory = repository.getCartHistory(token=token)?.map { it.toCartHistory() }
            emit(Resource.Success<List<CartHistory>>(data = cartHistory))
        } catch (e: HttpException) {
            emit(Resource.Error<List<CartHistory>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<CartHistory>>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}