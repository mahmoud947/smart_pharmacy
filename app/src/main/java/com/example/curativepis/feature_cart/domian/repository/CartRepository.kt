package com.example.curativepis.feature_cart.domian.repository

import com.example.curativepis.feature_cart.data.remote.response.dto.CartDto
import com.example.curativepis.feature_cart.domian.model.Cart

interface CartRepository {
    suspend fun getCart(token:String):CartDto?
}