package com.example.curativepis.feature_cart.presntation.cart_screen

import com.example.curativepis.feature_cart.domian.model.cart.Cart

data class CartScreenState(
    val isLoading: Boolean = false,
    val item: Cart = Cart("","", emptyList(), purchased = false, subTotal = 0.0,"", user_uid = ""),
    val error: String? = null,
    val isItemDeleted:Boolean=false,
    val itemDeletedErrorMessage:String?=null,
    val isCartPruchase:Boolean=false
)
