package com.example.curativepis.feature_scanner.presntaion.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ScaffoldState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.curativepis.core.navigation.BottomBarScreen
import com.example.curativepis.feature_scanner.presntaion.screen.camera_screen.CameraScreen
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreen

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.scannerNavGraph(
    scaffoldState: ScaffoldState,
    navController: NavController,
) {
    navigation(
        startDestination = BottomBarScreen.Scanner.route,
        route = "SCANNER_ROUTE"
    ) {
        composable(route = BottomBarScreen.Scanner.route) {
            ScannerScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = ScannerScreens.CameraScreen.route){
            CameraScreen()
        }
    }
}