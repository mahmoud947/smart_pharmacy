package com.example.curativepis.core.navigation

import android.os.Build
import android.window.SplashScreen
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.core.presentation.screen.home_screen.util.homeNavGraph
import com.example.curativepis.feature_ath.presntation.screen.login_screen.LoginScreen
import com.example.curativepis.feature_ath.presntation.screen.signup_screen.SignUpScreen
import com.example.curativepis.feature_ath.presntation.screen.splash_screen.SplashScreen
import com.example.curativepis.feature_ath.presntation.util.AuthScreens
import com.example.curativepis.feature_ath.presntation.util.authNavGraph
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetailScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.DrugScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.SearchScreen
import com.example.curativepis.feature_drugs.presntation.util.DrugScreenArguments
import com.example.curativepis.feature_drugs.presntation.util.DrugsScreens
import com.example.curativepis.feature_drugs.presntation.util.drugsNavGraph
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.NewsScreen
import com.example.curativepis.feature_scanner.presntaion.screen.camera_screen.CameraScreen
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreen
import com.example.curativepis.feature_scanner.presntaion.util.ScannerScreens
import com.example.curativepis.feature_scanner.presntaion.util.scannerNavGraph

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MyNavGraph(navController: NavHostController, scaffoldState: ScaffoldState) {

    NavHost(
        navController = navController,
        startDestination = AuthScreens.SpalshScreen.route
    ) {
        composable(route = AuthScreens.SpalshScreen.route){
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,
            )
        }
        composable(route = AuthScreens.LoginScreen.route){
            LoginScreen(
                scaffoldState=scaffoldState,
                navController=navController,
                onNavigate = navController::navigate,
                onLogin = {
                    navController.popBackStack(
                        route = AuthScreens.LoginScreen.route,
                        inclusive = true
                    )
                    navController.navigate(route = HomeScreens.News.route)
                },

            )
        }
        composable(route = AuthScreens.SignUpScreen.route){
            SignUpScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = HomeScreens.News.route) {
            NewsScreen(scaffoldState = scaffoldState, navController = navController)
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

        composable(route = HomeScreens.Scanner.route) {
            ScannerScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = ScannerScreens.CameraScreen.route){
            CameraScreen()
        }
        composable(route = HomeScreens.Drugs.route) {
            DrugScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = DrugsScreens.SearchScreen.route){
            SearchScreen(navController = navController)
        }
        composable(
            route = DrugsScreens.DrugDetailScreen.route,
            arguments = listOf(navArgument(DrugScreenArguments.DRUG_DETAIL_SCREEN_ARGUMENT_KEY){
                type= NavType.StringType
            })
        ){
            val drugID=it.arguments?.getString(DrugScreenArguments.DRUG_DETAIL_SCREEN_ARGUMENT_KEY)
            DrugDetailScreen(drugID = drugID, navController = navController)
        }
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