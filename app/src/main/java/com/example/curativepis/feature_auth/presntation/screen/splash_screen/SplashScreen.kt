package com.example.curativepis.feature_auth.presntation.screen.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_auth.presntation.screen.splash_screen.view_model.SplashScreenViewModel
import com.example.curativepis.feature_auth.presntation.util.AuthScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@Composable
fun SplashScreen(
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    navController: NavController,
    viewModel: SplashScreenViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true ){
        delay(1000)
        viewModel.actionEventChannel.collect{event->
            when(event){
                is SplashScreenViewModel.ActionEvent.NavigateToHome->{
                    navController.navigate(HomeScreens.News.route){
                        popUpTo(navController.graph.findStartDestination().id){
                        inclusive=true
                        }
                        launchSingleTop = true
                        popUpToId
                        restoreState = true

                    }
                }
                is SplashScreenViewModel.ActionEvent.NavigateToLogin->{
                    navController.navigate(AuthScreens.LoginScreen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            inclusive=true
                        }
                        launchSingleTop = true
                        popUpToId
                        restoreState = true

                    }
                }
            }
        }


        onPopBackStack()
        onNavigate(AuthScreens.LoginScreen.route)

        }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Splash",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}