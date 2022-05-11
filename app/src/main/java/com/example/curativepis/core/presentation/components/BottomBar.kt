package com.example.curativepis.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_ath.presntation.util.AuthScreens
import com.example.curativepis.ui.theme.spacing

@Composable
fun BottomBar(navController: NavHostController, bottomBarSate: MutableState<Boolean>) {
    val screens = listOf(
        HomeScreens.News,
        HomeScreens.Drugs,
        HomeScreens.Scanner,
        HomeScreens.Cart,
        HomeScreens.Notifications,
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
    screen: HomeScreens,
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