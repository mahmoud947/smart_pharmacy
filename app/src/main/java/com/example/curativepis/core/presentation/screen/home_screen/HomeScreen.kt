package com.example.curativepis.core.presentation.screen.home_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.curativepis.R
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.core.navigation.MyNavGraph
import com.example.curativepis.core.presentation.components.BottomBar
import com.example.curativepis.core.presentation.components.DrawerCntent
import com.example.curativepis.core.presentation.screen.home_screen.view_model.HomeViewModel
import com.example.curativepis.feature_auth.presntation.util.AuthScreens
import com.example.curativepis.feature_cart.presntation.util.CartScreens
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

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
    val scop= rememberCoroutineScope()

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
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)) {
                    Image(painter = painterResource(id = R.drawable.app_logo), contentDescription = null)
                }


                Spacer(modifier = Modifier.height(MaterialTheme.spacing.xLarge))
                DrawerCntent(title = "Cart History", icon = Icons.Default.History, onClick = {
                    scop.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(CartScreens.CartHistoryScreen.route)
                },modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                DrawerCntent(title = "username: ${viewModel.drowerState.username}", icon = Icons.Default.Person, onClick = {

                },modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                DrawerCntent(title = "Email: ${viewModel.drowerState.email}",
                    icon = Icons.Default.Email,
                    onClick = {

                    },modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                DrawerCntent(title = "phone: ${viewModel.drowerState.phone}",
                    icon = Icons.Default.Phone,
                    onClick = {

                    },modifier = Modifier.weight(1f))

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f))
                DrawerCntent(title = "Logout",
                    icon = Icons.Default.Logout,
                    onClick = {
                        scop.launch {
                        scaffoldState.drawerState.close()
                        }
                        viewModel.onEvent(HomeScreenEvent.SignOut)
                        navController.navigate(AuthScreens.LoginScreen.route) {
                            popUpTo(HomeScreens.News.route) {
                                inclusive = true
                            }
                        }
                    }, modifier = Modifier.weight(10f))
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

