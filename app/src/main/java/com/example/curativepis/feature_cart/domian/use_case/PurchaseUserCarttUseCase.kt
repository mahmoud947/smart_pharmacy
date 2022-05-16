package com.example.curativepis.feature_cart.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_cart.data.mapper.toCart
import com.example.curativepis.feature_cart.data.remote.response.CartBaseResponse
import com.example.curativepis.feature_cart.domian.model.Cart
import com.example.curativepis.feature_cart.domian.repository.CartRepository
import com.example.curativepis.feature_drugs.data.mapper.toDrug
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PurchaseUserCarttUseCase(
    private val repository: CartRepository,
) {
    operator fun invoke(token: String):Flow<Resource<CartBaseResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.purchaseCart(token=token)
            emit(Resource.Success<CartBaseResponse>(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error<CartBaseResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CartBaseResponse>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}