package com.example.curativepis.core.navigation

import android.os.Build
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.core.presentation.screen.home_screen.util.homeNavGraph
import com.example.curativepis.feature_ath.presntation.util.authNavGraph

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavGraph(navController: NavHostController, scaffoldState: ScaffoldState) {

    val startDies=remember{ mutableStateOf(Constants.AUTH_NAVGRAPH_ROOT) }
    NavHost(
        navController = navController,
        startDestination = Constants.AUTH_NAVGRAPH_ROOT,
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