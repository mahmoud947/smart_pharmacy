package com.example.curativepis.feature_cart.data.repository

import com.example.curativepis.feature_cart.data.CartApi
import com.example.curativepis.feature_cart.data.remote.request.DeleteCartItemRequest
import com.example.curativepis.feature_cart.data.remote.response.CartBaseResponse
import com.example.curativepis.feature_cart.data.remote.response.dto.CartDto
import com.example.curativepis.feature_cart.domian.model.Cart
import com.example.curativepis.feature_cart.domian.repository.CartRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api:CartApi
):CartRepository {
    override suspend fun getCart(token: String): CartDto? =
        api.getCart(token = token)

    override suspend fun deleteItemFromCart(
        token: String,
        deleteCartItemId: String,
    ): CartBaseResponse? =api.deleteItemFromCart(token = token, deleteCartItemId = deleteCartItemId)


}