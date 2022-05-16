package com.example.curativepis.feature_cart.domian.model.cart

data class Cart(
    val _id: String,
    val createdAt: String,
    val items: List<CartItem>,
    val purchased: Boolean,
    val subTotal: Double,
    val updatedAt: String,
    val user_uid: String
)