package com.example.curativepis.feature_cart.data.remote.response.dto

data class CartDto(
    val _id: String?,
    val createdAt: String?,
    val items: List<CartItemDto>?,
    val purchased: Boolean?,
    val subTotal: Double?,
    val updatedAt: String?,
    val user_uid: String?
)