package com.example.curativepis.feature_cart.data

import com.example.curativepis.feature_cart.data.remote.request.DeleteCartItemRequest
import com.example.curativepis.feature_cart.data.remote.response.CartBaseResponse
import com.example.curativepis.feature_cart.data.remote.response.dto.CartDto
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
}