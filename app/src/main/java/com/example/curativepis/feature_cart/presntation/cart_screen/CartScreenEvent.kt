package com.example.curativepis.feature_cart.presntation.cart_screen

sealed class CartScreenEvent{
  object GetCart:CartScreenEvent()
  data class DeleteCartItem(val itemId:String):CartScreenEvent()
  object PurchaseCart:CartScreenEvent()
}
