package com.example.curativepis.feature_cart.presntation.cart_screen

sealed class CartScreenEvent{
    data class GetCart(val token:String):CartScreenEvent()
}
