package com.example.curativepis.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.curativepis.feature_drugs.presntation.screen.DrugScreen
import com.example.curativepis.feature_news.presntaion.screen.NewsScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.News.route
    ){
        composable(route = BottomBarScreen.News.route){
           NewsScreen()
        }
        composable(route = BottomBarScreen.Drugs.route){
            DrugScreen()
        }
        composable(route = BottomBarScreen.Scanner.route){
            ScannerScreen()
        }
        composable(route = BottomBarScreen.Notifications.route){
            NotificationsScreen()
        }
        composable(route = BottomBarScreen.Cart.route){
            CartScreen()
        }

    }
}



@Composable
fun ScannerScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color(0XFF19D3DA),
                darkIcons = false
            )
        }
        Text(
            text = "Scanner",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
@Composable
fun NotificationsScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Notifications",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
@Composable
fun CartScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Cart",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}