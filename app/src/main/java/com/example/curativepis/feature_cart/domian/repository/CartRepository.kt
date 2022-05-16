package com.example.curativepis.feature_cart.domian.repository

import com.example.curativepis.feature_cart.data.remote.response.CartBaseResponse
import com.example.curativepis.feature_cart.data.remote.response.dto.CartDto

interface CartRepository {
    suspend fun getCart(token: String): CartDto?
    suspend fun deleteItemFromCart(token: String, deleteCartItemId: String): CartBaseResponse?
    suspend fun purchaseCart(token: String):CartBaseResponse?
}