package com.example.curativepis.feature_cart.presntation.cart_history_screen

import com.example.curativepis.feature_cart.domian.model.cart_history.CartHistory

data class CartHistoryScreenState(
    val isLoading: Boolean = false,
    val item: List<CartHistory> = emptyList(),
    val error: String? = null,
)
