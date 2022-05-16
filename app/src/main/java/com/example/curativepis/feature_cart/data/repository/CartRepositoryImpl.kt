package com.example.curativepis.feature_cart.data.repository

import com.example.curativepis.feature_cart.data.remote.CartApi
import com.example.curativepis.feature_cart.data.remote.response.CartBaseResponse
import com.example.curativepis.feature_cart.data.remote.response.dto.cart.CartDto
import com.example.curativepis.feature_cart.data.remote.response.dto.cart_history.CartHistoryDto
import com.example.curativepis.feature_cart.domian.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api: CartApi
):CartRepository {
    override suspend fun getCart(token: String): CartDto? =
        api.getCart(token = token)

    override suspend fun deleteItemFromCart(
        token: String,
        deleteCartItemId: String,
    ): CartBaseResponse? =api.deleteItemFromCart(token = token, deleteCartItemId = deleteCartItemId)

    override suspend fun purchaseCart(token: String): CartBaseResponse? = api.purchaseCart(token = token)
    override suspend fun getCartHistory(token: String): List<CartHistoryDto>? =api.getCartHistory(token = token)


}