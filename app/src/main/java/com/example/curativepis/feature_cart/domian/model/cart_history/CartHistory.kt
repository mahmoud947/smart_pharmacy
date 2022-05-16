package com.example.curativepis.feature_cart.domian.model.cart_history

data class CartHistory(
    val _id: String,
    val createdAt: String,
    val items: List<CartHistoryItem>,
    val purchased: Boolean,
    val subTotal: Double,
    val updatedAt: String,
    val user_uid: String
)