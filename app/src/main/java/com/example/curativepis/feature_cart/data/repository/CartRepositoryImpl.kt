package com.example.curativepis.feature_cart.data.repository

import com.example.curativepis.feature_cart.data.CartApi
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

}