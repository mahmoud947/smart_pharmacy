package com.example.curativepis.feature_drugs.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_cart.data.mapper.toCart
import com.example.curativepis.feature_cart.domian.model.cart.Cart
import com.example.curativepis.feature_drugs.data.remote.request.AddItemToCartReq
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AddItemToCartUseCase(
    private val repository: DrugsRepository
) {
    operator fun invoke(token:String,addItemToCartReq: AddItemToCartReq): Flow<Resource<Cart>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.addItemToCart(token = token, addItemToCartReq = addItemToCartReq)?.toCart()
            emit(Resource.Success<Cart>(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error<Cart>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Cart>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}