package com.example.curativepis.feature_cart.data.remote

import com.example.curativepis.feature_cart.data.remote.response.CartBaseResponse
import com.example.curativepis.feature_cart.data.remote.response.dto.cart.CartDto
import com.example.curativepis.feature_cart.data.remote.response.dto.cart_history.CartHistoryDto
import retrofit2.http.*

interface CartApi {
    @GET("cart")
    suspend fun getCart(
        @Header("Authorization") token: String,
    ): CartDto?


    @DELETE("cart/{itemId}")
    suspend fun deleteItemFromCart(
        @Header("Authorization") token: String,
        @Path("itemId") deleteCartItemId: String
    ): CartBaseResponse?

    @POST("cart")
    suspend fun purchaseCart(
        @Header("Authorization") token: String,
    ):CartBaseResponse?


    @GET("cart/history")
    suspend fun getCartHistory(
        @Header("Authorization") token: String,
    ): List<CartHistoryDto>?


}