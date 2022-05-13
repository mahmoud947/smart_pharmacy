package com.example.curativepis.feature_ath.presntation.util

import androidx.compose.material.ScaffoldState
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_ath.presntation.screen.login_screen.LoginScreen
import com.example.curativepis.feature_ath.presntation.screen.splash_screen.SplashScreen

fun NavGraphBuilder.authNavGraph(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
){
    navigation(
        startDestination = AuthScreens.SpalshScreen.route,
        route = Constants.AUTH_NAVGRAPH_ROOT
    ){
        composable(
            route = AuthScreens.LoginScreen.route
        ){
            LoginScreen(
                scaffoldState=scaffoldState,
                navController=navController,
                onLogin={

                    navController.navigate(route = HomeScreens.News.route)
                }
            )
        }
        
        composable(route = AuthScreens.SpalshScreen.route){
            SplashScreen()
        }
        composable(route = AuthScreens.SignUpScreen.route){
          //  SignUpScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = AuthScreens.OTPScreen.route,
            arguments = listOf(navArgument(AuthScreenArguments.OTP_SCREEN_ARGUMENT_PHONE_KEY){
                type= NavType.StringType
            })
        ){
//            val userDetails=it.arguments?.getString(AuthScreenArguments.User_DETAIL_SCREEN_ARGUMENT_KEY)
//            OTPScreen(navController = navController,onNavigate = navController::navigate,userDetails=userDetails, activite = activity)
        }


    }

}