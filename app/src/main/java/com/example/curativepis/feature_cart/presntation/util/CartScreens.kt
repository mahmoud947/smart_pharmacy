package com.example.curativepis.feature_cart.presntation.util



sealed class CartScreens(val route:String){
    object CartHistoryScreen:CartScreens(route = "cart_history_screen")
}