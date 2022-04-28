package com.example.curativepis.core.presentation.screen.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.curativepis.core.navigation.BottomBarScreen
import com.example.curativepis.core.navigation.BottomNavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ) {

        Box(modifier = Modifier.padding(it).clip(
            RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        )) {
            BottomNavGraph(navController = navController)
        }

    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.News,
        BottomBarScreen.Drugs,
        BottomBarScreen.Scanner,
        BottomBarScreen.Cart,
        BottomBarScreen.Notifications,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        backgroundColor = Color(0xFF19D3DA),
        modifier = Modifier
            .graphicsLayer {
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                clip = true
            },


        ) {

        screens.forEach { screen ->
            AddItem(screen = screen,
                currentDestination = currentDestination,
                navController = navController)
        }

    }
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
    BottomNavigationItem(
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = screen.title,
                overflow = TextOverflow.Visible,
                maxLines = 1,
                fontWeight = FontWeight.Bold)

        },
        icon = {

            if (selected) {
                Icon(
                    painterResource(id = screen.icon),
                    contentDescription = screen.title,
                    Modifier.size(28.dp),
                    tint = Color.White,
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