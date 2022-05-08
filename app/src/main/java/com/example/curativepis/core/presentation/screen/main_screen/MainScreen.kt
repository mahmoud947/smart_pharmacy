package com.example.curativepis.core.presentation.screen.main_screen

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
import com.example.curativepis.core.navigation.BottomBarScreen
import com.example.curativepis.core.navigation.BottomNavGraph
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
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
            BottomBarScreen.Scanner.route -> bottomBarState.value = true
            BottomBarScreen.News.route -> bottomBarState.value = true
            BottomBarScreen.Cart.route -> bottomBarState.value = true
            BottomBarScreen.Drugs.route -> bottomBarState.value = true
            BottomBarScreen.Notifications.route -> bottomBarState.value = true
            else -> bottomBarState.value = false
        }
        BottomNavGraph(navController = navController, scaffoldState = scaffoldState)
    }
}

@Composable
fun BottomBar(navController: NavHostController, bottomBarSate: MutableState<Boolean>) {
    val screens = listOf(
        BottomBarScreen.News,
        BottomBarScreen.Drugs,
        BottomBarScreen.Scanner,
        BottomBarScreen.Cart,
        BottomBarScreen.Notifications,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    AnimatedVisibility(
        visible = bottomBarSate.value,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
        content = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .height(60.dp)
                    .graphicsLayer {
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                        clip = true
                    },
                elevation = MaterialTheme.spacing.regulator


            ) {

                screens.forEach { screen ->
                    AddItem(screen = screen,
                        currentDestination = currentDestination,
                        navController = navController)
                }

            }
        }
    )

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    val dark = isSystemInDarkTheme()
    BottomNavigationItem(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = screen.title,
                overflow = TextOverflow.Visible,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                color = if (dark) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.surface
            )

        },
        icon = {
            if (selected) {
                Icon(
                    painterResource(id = screen.icon),
                    contentDescription = screen.title,
                    Modifier.size(30.dp),
                    tint = if (dark) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.surface,
                )
            } else {
                Icon(
                    painterResource(id = screen.icon),
                    contentDescription = screen.title,
                    Modifier.size(24.dp),
                    tint = Color(0xFFEEEEEE)
                )
            }

        },
        selected = selected,
        onClick = {

            navController.navigate(screen.route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true

            }


        },
        alwaysShowLabel = false,
        enabled = true,
        selectedContentColor = Color.White,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

        )
}