package com.example.curativepis.feature_cart.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_cart.data.mapper.toCart
import com.example.curativepis.feature_cart.domian.model.cart.Cart
import com.example.curativepis.feature_cart.domian.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCartUseCase(
    private val repository: CartRepository,
) {
    operator fun invoke(token: String):Flow<Resource<Cart>> = flow {
        try {
            emit(Resource.Loading())
            val cart = repository.getCart(token = token)?.toCart()
            emit(Resource.Success<Cart>(data = cart))
        } catch (e: HttpException) {
            emit(Resource.Error<Cart>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Cart>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}