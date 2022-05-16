package com.example.curativepis.feature_cart.domian.model.cart_history

data class CartHistoryItem(
    val drugId: String,
    val drug_name: String,
    val image: String,
    val price: Double,
    val quantity: Int,
    val total: Double
)