package com.example.curativepis.core.navigation

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_auth.presntation.screen.login_screen.LoginScreen
import com.example.curativepis.feature_auth.presntation.screen.otp_screen.OTPScreen
import com.example.curativepis.feature_auth.presntation.screen.signup_screen.SignUpScreen
import com.example.curativepis.feature_auth.presntation.screen.splash_screen.SplashScreen
import com.example.curativepis.feature_auth.presntation.util.AuthScreenArguments
import com.example.curativepis.feature_auth.presntation.util.AuthScreens
import com.example.curativepis.feature_cart.presntation.cart_screen.CartScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetailScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.DrugScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.SearchScreen
import com.example.curativepis.feature_drugs.presntation.util.DrugScreenArguments
import com.example.curativepis.feature_drugs.presntation.util.DrugsScreens
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.NewsScreen
import com.example.curativepis.feature_scanner.presntaion.screen.camera_screen.CameraScreen
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreen
import com.example.curativepis.feature_scanner.presntaion.util.ScannerScreens

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MyNavGraph(scaffoldState: ScaffoldState,navController:NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AuthScreens.SpalshScreen.route
    ) {
        composable(route = AuthScreens.SpalshScreen.route) {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,
                navController = navController
            )
        }
        composable(route = AuthScreens.LoginScreen.route) {
            LoginScreen(
                scaffoldState = scaffoldState,
                navController = navController,
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
        composable(route = AuthScreens.SignUpScreen.route) {
            SignUpScreen(scaffoldState = scaffoldState,
                navController = navController,
                onNavigate = navController::navigate)
        }
        composable(route = AuthScreens.OTPScreen.route,
            arguments = listOf(
                navArgument(AuthScreenArguments.OTP_SCREEN_ARGUMENT_PHONE_KEY) {
                    type = NavType.StringType
                },
                navArgument(AuthScreenArguments.OTP_SCREEN_ARGUMENT_EMAIL_KEY) {
                    type = NavType.StringType
                },
                navArgument(AuthScreenArguments.OTP_SCREEN_ARGUMENT_ISMALE_KEY) {
                    type = NavType.BoolType
                },
                navArgument(AuthScreenArguments.OTP_SCREEN_ARGUMENT_USERNAME_KEY) {
                    type = NavType.StringType
                },
                navArgument(AuthScreenArguments.OTP_SCREEN_ARGUMENT_PASSWORD_KEY) {
                    type = NavType.StringType
                },
                navArgument(AuthScreenArguments.OTP_SCREEN_ARGUMENT_DTOD_KEY) {
                    type = NavType.StringType
                },
            )
        ) {
            val phone = it.arguments?.getString(AuthScreenArguments.OTP_SCREEN_ARGUMENT_PHONE_KEY)
            val email = it.arguments?.getString(AuthScreenArguments.OTP_SCREEN_ARGUMENT_EMAIL_KEY)
            val isMale = it.arguments?.getBoolean(AuthScreenArguments.OTP_SCREEN_ARGUMENT_ISMALE_KEY)
            val username = it.arguments?.getString(AuthScreenArguments.OTP_SCREEN_ARGUMENT_USERNAME_KEY)
            val password = it.arguments?.getString(AuthScreenArguments.OTP_SCREEN_ARGUMENT_PASSWORD_KEY)
            val dto = it.arguments?.getString(AuthScreenArguments.OTP_SCREEN_ARGUMENT_DTOD_KEY)
            OTPScreen(
                navController = navController,
                onNavigate = navController::navigate,
                phone = phone,
                email = email,
                isMale = isMale,
                username = username,
                password = password,
                dto = dto,
            )
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
            CartScreen(scaffoldState = scaffoldState)
        }

        composable(route = HomeScreens.Scanner.route) {
            ScannerScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = ScannerScreens.CameraScreen.route) {
            CameraScreen()
        }
        composable(route = HomeScreens.Drugs.route) {
            DrugScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = DrugsScreens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = DrugsScreens.DrugDetailScreen.route,
            arguments = listOf(navArgument(DrugScreenArguments.DRUG_DETAIL_SCREEN_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) {
            val drugID =
                it.arguments?.getString(DrugScreenArguments.DRUG_DETAIL_SCREEN_ARGUMENT_KEY)
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

