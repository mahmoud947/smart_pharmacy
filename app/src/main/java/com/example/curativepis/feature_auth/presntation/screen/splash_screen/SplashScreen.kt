package com.example.curativepis.feature_auth.presntation.screen.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.curativepis.R
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_auth.presntation.screen.splash_screen.view_model.SplashScreenViewModel
import com.example.curativepis.feature_auth.presntation.util.AuthScreens
import com.example.curativepis.ui.theme.darckGreyBackground
import com.example.curativepis.ui.theme.spacing
import com.example.curativepis.ui.theme.whiteSmoke
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    navController: NavController,
    viewModel: SplashScreenViewModel = hiltViewModel(),
) {

    val firebaseAuth = FirebaseAuth.getInstance()
    LaunchedEffect(key1 = true) {









        delay(1000)
        viewModel.actionEventChannel.collect { event ->
            when (event) {
                is SplashScreenViewModel.ActionEvent.NavigateToHome -> {

                    navController.navigate(HomeScreens.News.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        popUpToId
                        restoreState = true

                    }
                }

                is SplashScreenViewModel.ActionEvent.NavigateToLogin -> {
                    navController.navigate(AuthScreens.LoginScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
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

    Column(modifier = Modifier
        .fillMaxSize()
        .background(darckGreyBackground),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.height(200.dp).padding(top = MaterialTheme.spacing.large).weight(4f, fill = true)) {
            Image(painter = painterResource(id = R.drawable.app_logo), contentDescription = null)
        }

        Text(
            text = stringResource(id = R.string.app_sub_lable),
            style = MaterialTheme.typography.subtitle1.copy(color = whiteSmoke),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = MaterialTheme.spacing.medium)

        )
    }
}