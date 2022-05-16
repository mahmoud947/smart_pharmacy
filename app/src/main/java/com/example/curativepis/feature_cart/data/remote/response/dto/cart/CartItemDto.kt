package com.example.curativepis.feature_cart.data.remote.response.dto.cart

data class CartItemDto(
    val drugId: String?,
    val drug_name: String?,
    val image: String?,
    val price: Double?,
    val quantity: Int?,
    val total: Double?
)