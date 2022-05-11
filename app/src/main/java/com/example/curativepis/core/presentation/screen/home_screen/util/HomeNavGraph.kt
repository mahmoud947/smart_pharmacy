package com.example.curativepis.core.presentation.screen.home_screen.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.core.navigation.CartScreen
import com.example.curativepis.core.navigation.NotificationsScreen
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
        startDestination = HomeScreens.News.route,
        route = Constants.HOME_NAVGRAPH_ROOT
    ){
        composable(route = HomeScreens.News.route) {
            NewsScreen(scaffoldState = scaffoldState)
        }
        composable(route = HomeScreens.Drugs.route) {
            DrugScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = HomeScreens.Scanner.route) {
            ScannerScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = HomeScreens.Notifications.route) {
            NotificationsScreen()
        }
        composable(route = HomeScreens.Cart.route) {
            CartScreen()
        }

        scannerNavGraph(scaffoldState = scaffoldState, navController = navController)
        drugsNavGraph(scaffoldState = scaffoldState, navController = navController)
    }
}