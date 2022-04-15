package com.example.curativepis.core.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.curativepis.R

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon:Int
){
    object News:BottomBarScreen(
        route = "news",
        title = "News",
        icon = R.drawable.news_24dp
    )
    object Drugs:BottomBarScreen(
        route = "drugs",
        title = "Drugs",
        icon = R.drawable.vaccines_24dp
    )
    object Scanner:BottomBarScreen(
        route = "scanner",
        title = "Scanner",
        icon = R.drawable.scanner_24dp
    )
    object Cart:BottomBarScreen(
        route = "cart",
        title = "Cart",
        icon = R.drawable.cart_24dp
    )
    object Notifications:BottomBarScreen(
        route = "notifications",
        title = "Notifications",
        icon = R.drawable.notifications_24dp
    )

}
