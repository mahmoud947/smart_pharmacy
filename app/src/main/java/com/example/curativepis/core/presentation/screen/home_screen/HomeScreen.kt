package com.example.curativepis.core.presentation.screen.home_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.core.navigation.NavGraph
import com.example.curativepis.core.presentation.components.BottomBar
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    )
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.primary


    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = false
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerBackgroundColor = MaterialTheme.colors.primary,
        drawerElevation = MaterialTheme.spacing.medium,
        drawerShape = RoundedCornerShape(20.dp),
        drawerContent = {

            Text("Drawer title", modifier = Modifier.padding(16.dp))
        },
        bottomBar = {

            BottomBar(navController = navController, bottomBarSate = bottomBarState)
        },
    ) {

        when (currentRoute) {
            HomeScreens.Scanner.route -> bottomBarState.value = true
            HomeScreens.News.route -> bottomBarState.value = true
            HomeScreens.Cart.route -> bottomBarState.value = true
            HomeScreens.Drugs.route -> bottomBarState.value = true
            HomeScreens.Notifications.route -> bottomBarState.value = true
            else -> bottomBarState.value = false
        }
        NavGraph(navController = navController, scaffoldState = scaffoldState)
    }
}

