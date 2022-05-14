package com.example.curativepis.feature_cart.data

import com.example.curativepis.feature_cart.data.remote.response.dto.CartDto
import retrofit2.http.GET
import retrofit2.http.Header

interface CartApi {
    @GET("cart")
  suspend fun getCart(
        @Header("Authorization") token:String,
    ):CartDto?
}