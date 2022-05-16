package com.example.curativepis.feature_cart.data.remote.response.dto.cart_history

data class CartHistoryDto(
    val _id: String?,
    val createdAt: String?,
    val items: List<CartHistoryItemDto>?,
    val purchased: Boolean?,
    val subTotal: Double?,
    val updatedAt: String?,
    val user_uid: String?
)