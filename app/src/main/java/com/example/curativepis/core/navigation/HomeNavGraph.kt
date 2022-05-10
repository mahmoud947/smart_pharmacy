package com.example.curativepis.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.DrugScreen
import com.example.curativepis.feature_drugs.presntation.util.drugsNavGraph
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.NewsScreen
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreen
import com.example.curativepis.feature_scanner.presntaion.util.scannerNavGraph

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController, scaffoldState: ScaffoldState
){
    navigation(
        startDestination = BottomBarScreen.News.route,
        route = "HOME_ROOT"
    ){
        composable(route = BottomBarScreen.News.route) {
            NewsScreen(scaffoldState = scaffoldState)
        }
        composable(route = BottomBarScreen.Drugs.route) {
            DrugScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = BottomBarScreen.Scanner.route) {
            ScannerScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = BottomBarScreen.Notifications.route) {
            NotificationsScreen()
        }
        composable(route = BottomBarScreen.Cart.route) {
            CartScreen()
        }

        scannerNavGraph(scaffoldState = scaffoldState, navController = navController)
        drugsNavGraph(scaffoldState = scaffoldState, navController = navController)
    }
}