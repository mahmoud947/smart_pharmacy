package com.example.curativepis.feature_scanner.presntaion.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ScaffoldState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_scanner.presntaion.screen.camera_screen.CameraScreen
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreen

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.scannerNavGraph(
    scaffoldState: ScaffoldState,
    navController: NavController,
) {
    navigation(
        startDestination = HomeScreens.Scanner.route,
        route = Constants.SCANNER_NAVGRAPH_ROOT
    ) {
        composable(route = HomeScreens.Scanner.route) {
            ScannerScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = ScannerScreens.CameraScreen.route){
            CameraScreen()
        }
    }
}