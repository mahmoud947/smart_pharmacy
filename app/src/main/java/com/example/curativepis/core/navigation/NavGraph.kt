package com.example.curativepis.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.curativepis.feature_ath.presntation.screen.login_screen.LoginScreen
import com.example.curativepis.feature_ath.presntation.util.AuthScreens
import com.example.curativepis.feature_ath.presntation.util.authNavGraph
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.DrugScreen
import com.example.curativepis.feature_drugs.presntation.util.drugsNavGraph
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.NewsScreen
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreen
import com.example.curativepis.feature_scanner.presntaion.util.scannerNavGraph

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BottomNavGraph(navController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(
        navController = navController,
        startDestination = "Auth_ROOT"
    ) {
        authNavGraph(scaffoldState = scaffoldState,navController=navController)
        homeNavGraph(scaffoldState = scaffoldState, navController = navController)

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