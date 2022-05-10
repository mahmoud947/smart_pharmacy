package com.example.curativepis.feature_ath.presntation.screen.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.R
import com.example.curativepis.core.navigation.BottomBarScreen
import com.example.curativepis.core.presentation.screen.main_screen.components.ButtonWithElevation
import com.example.curativepis.core.presentation.screen.main_screen.components.DefaultTextField
import com.example.curativepis.feature_ath.presntation.screen.login_screen.view_model.LoginScreenViewModel
import com.example.curativepis.ui.theme.darckGreyBackground
import com.example.curativepis.ui.theme.spacing
import com.example.curativepis.ui.theme.whiteSmoke

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val state = viewModel.uiState.value
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is LoginScreenViewModel.ValidationEvent.Success -> {
                    navController.popBackStack()
                    navController.navigate(route = "HOME_ROOT")
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darckGreyBackground)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(2f))
        Text(text = stringResource(id = R.string.app_label),
            style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.primaryVariant))

        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(id = R.string.app_sub_lable),
            style = MaterialTheme.typography.subtitle1.copy(color = whiteSmoke))
        Spacer(modifier = Modifier.weight(2f))
        Image(painter = painterResource(id = R.drawable.app_logo), contentDescription = null)
        Spacer(modifier = Modifier.weight(3f))
        DefaultTextField(value = state.username, label = "Username", onTextChange = {
            viewModel.onEvent(LoginScreenEvent.UsernameChanged(it))
        }, isError = state.usernameErrorMessage!=null)

        if (state.usernameErrorMessage != null) {
            Text(
                text = state.usernameErrorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.medium)
                    .align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        DefaultTextField(value = state.password, label = "Password", onTextChange = {
            viewModel.onEvent(LoginScreenEvent.PasswordChanged(it))
        }, isError = state.passwordErrorMessage!=null)
        if (state.passwordErrorMessage != null) {
            Text(
                text = state.passwordErrorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.medium)
                    .align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        ButtonWithElevation(
            onClick = {
                      viewModel.onEvent(LoginScreenEvent.Login)
            },
            text = "Sign in",
            modifier = Modifier
                .width(MaterialTheme.spacing.largeButtonX)
                .height(MaterialTheme.spacing.largeButtonH)
                .padding(horizontal = MaterialTheme.spacing.large)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        ButtonWithElevation(
            onClick = { /*TODO*/ },
            text = "Sign in",
            modifier = Modifier
                .width(MaterialTheme.spacing.largeButtonX)
                .height(MaterialTheme.spacing.largeButtonH)
                .padding(horizontal = MaterialTheme.spacing.large)
        )


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.xLarge))
        Box(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(darckGreyBackground),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Don't have an account ? ",
                    style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onPrimary)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.regulator))
                Text(
                    text = "Create Now !",
                    style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primaryVariant)
                )

            }
        }
        Spacer(modifier = Modifier.weight(1f))

    }

}



