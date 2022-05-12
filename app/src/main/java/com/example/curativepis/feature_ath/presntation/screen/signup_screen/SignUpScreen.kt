package com.example.curativepis.feature_ath.presntation.screen.signup_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.R
import com.example.curativepis.core.presentation.components.ButtonWithElevation
import com.example.curativepis.core.presentation.components.DefaultTextField
import com.example.curativepis.core.presentation.components.DefaultTopAppBar
import com.example.curativepis.feature_ath.presntation.screen.login_screen.view_model.LoginScreenViewModel
import com.example.curativepis.feature_ath.presntation.screen.signup_screen.components.DataPicker
import com.example.curativepis.feature_ath.presntation.screen.signup_screen.components.GenderSection
import com.example.curativepis.feature_ath.presntation.screen.signup_screen.view_model.SignUpScreenViewModel
import com.example.curativepis.feature_ath.presntation.util.AuthScreens
import com.example.curativepis.ui.theme.spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    scaffoldState: ScaffoldState,
    navController: NavController,
    onNavigate: (String) -> Unit = {},
    viewModel: SignUpScreenViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val state = viewModel.uiState.value

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is SignUpScreenViewModel.ValidationEvent.Success -> {
                    viewModel.onEvent(SignUpScreenEvent.PassUserObject)
                   if (viewModel.userAsJson.value!=null){
                       onNavigate(AuthScreens.OTPScreen.passUserDetails(userDetails = viewModel.userAsJson.value.toString()))
                   }

                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),

    ) {
        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            DefaultTopAppBar(navigationIcon = Icons.Default.ArrowBack, onClick = {})
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)) {
                Image(painter = painterResource(id = R.drawable.ic_undraw_sign_up_image),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize())
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xLarge))
            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                DefaultTextField(value = state.username, label = "Username", onTextChange = {
                    viewModel.onEvent(SignUpScreenEvent.UsernameChanged(it))
                }, isError = false)
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
                DefaultTextField(value = state.email, label = "E-Mail", onTextChange = {
                    viewModel.onEvent(SignUpScreenEvent.EmailChanged(it))
                }, isError = false)
                if (state.emailErrorMessage != null) {
                    Text(
                        text = state.emailErrorMessage,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = MaterialTheme.spacing.medium)
                            .align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
                DefaultTextField(value =state.phone, label = "Phone number", onTextChange = {
                    viewModel.onEvent(SignUpScreenEvent.PhoneChanged(it))
                }, isError = false)
                if (state.phoneErrorMessage != null) {
                    Text(
                        text = state.phoneErrorMessage,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = MaterialTheme.spacing.medium)
                            .align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
                DefaultTextField(value = state.password, label = "Password", onTextChange = {
                    viewModel.onEvent(SignUpScreenEvent.PasswordChanged(it))
                }, isError = false)
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

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
                DefaultTextField(value =state.confirmPassword, label = "Confirm Password", onTextChange = {
                    viewModel.onEvent(SignUpScreenEvent.ConfirmPasswordChanged(state.password,it))
                }, isError = false)
                if (state.confirmPasswordErrorMessage != null) {
                    Text(
                        text = state.confirmPasswordErrorMessage,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = MaterialTheme.spacing.medium)
                            .align(Alignment.End)
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.regulator))
                DataPicker(context = context, getDateValue = {
                    viewModel.onEvent(SignUpScreenEvent.BirthDateChanged(it))
                })
                GenderSection(isMale = {
                    viewModel.onEvent(SignUpScreenEvent.GenderChanged(it))
                })
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.xLarge))
                ButtonWithElevation(
                    onClick = {
                              viewModel.onEvent(SignUpScreenEvent.SignUp)
                    },
                    text = "SignUp",
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.large)
                        .width(MaterialTheme.spacing.largeButtonX)
                        .height(MaterialTheme.spacing.largeButtonH)
                )
            }
        }



    }

}