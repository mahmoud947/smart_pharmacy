package com.example.curativepis.core.presentation.screen.home_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.core.navigation.MyNavGraph
import com.example.curativepis.core.presentation.components.BottomBar
import com.example.curativepis.core.presentation.screen.home_screen.view_model.HomeViewModel
import com.example.curativepis.feature_auth.presntation.util.AuthScreens
import com.example.curativepis.feature_cart.presntation.util.CartScreens
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {

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
        drawerContentColor = MaterialTheme.colors.primary,
        drawerContent = {

            Spacer(modifier = Modifier.height(100.dp))
            Text("Drawer title", modifier = Modifier
                .padding(16.dp)
                .clickable {
                    viewModel.onEvent(HomeScreenEvent.SignOut)
                    navController.navigate(AuthScreens.LoginScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        popUpToId
                        restoreState = true
                    }
                })
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                               navController.navigate(CartScreens.CartHistoryScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Cart History",
                    style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onPrimary)
                )
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }




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
        MyNavGraph( scaffoldState = scaffoldState, navController = navController)
    }
}

