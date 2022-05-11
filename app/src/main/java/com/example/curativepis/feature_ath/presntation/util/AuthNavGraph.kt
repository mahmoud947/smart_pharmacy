package com.example.curativepis.feature_ath.presntation.util

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_ath.presntation.screen.login_screen.LoginScreen

fun NavGraphBuilder.authNavGraph(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
){
    navigation(
        startDestination = AuthScreens.LoginScreen.route,
        route = Constants.AUTH_NAVGRAPH_ROOT
    ){
        composable(
            route = AuthScreens.LoginScreen.route
        ){
            LoginScreen(
                scaffoldState=scaffoldState,
                navController=navController,
            )
        }

    }

}