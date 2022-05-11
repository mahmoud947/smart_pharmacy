package com.example.curativepis.core.presentation.screen.home_screen.util

import com.example.curativepis.R

sealed class HomeScreens(
    val route:String,
    val title:String,
    val icon:Int
){
    object News: HomeScreens(
        route = "news",
        title = "News",
        icon = R.drawable.news_24dp
    )
    object Drugs: HomeScreens(
        route = "drugs",
        title = "Drugs",
        icon = R.drawable.vaccines_24dp
    )
    object Scanner: HomeScreens(
        route = "scanner",
        title = "Scanner",
        icon = R.drawable.scanner_24dp
    )
    object Cart: HomeScreens(
        route = "cart",
        title = "Cart",
        icon = R.drawable.cart_24dp
    )
    object Notifications: HomeScreens(
        route = "notifications",
        title = "Notifications",
        icon = R.drawable.notifications_24dp
    )


}
