package com.example.curativepis.feature_ath.presntation.util

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.curativepis.feature_ath.presntation.screen.login_screen.LoginScreen

fun NavGraphBuilder.authNavGraph(
    scaffoldState: ScaffoldState,
    navController: NavController,
){
    navigation(
        startDestination = AuthScreens.LoginScreen.rout,
        route = "Auth_ROOT"
    ){
        composable(
            route = AuthScreens.LoginScreen.rout
        ){
            LoginScreen(
                scaffoldState=scaffoldState,
                navController=navController
            )
        }
    }

}