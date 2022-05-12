package com.example.curativepis.feature_ath.presntation.screen.otp_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.R
import com.example.curativepis.core.presentation.components.DefaultTopAppBar
import com.example.curativepis.feature_ath.presntation.screen.otp_screen.components.OtpTextField
import com.example.curativepis.feature_ath.presntation.screen.otp_screen.view_model.OTPScreenViewModel
import com.example.curativepis.ui.theme.spacing

@Composable
fun OTPScreen(
    navController: NavController,
    onNavigate: (String) -> Unit = {},
    viewModel: OTPScreenViewModel = hiltViewModel(),
    userDetails:String?
) {
    val context = LocalContext.current
    val state = viewModel.uiState.value

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is OTPScreenViewModel.ValidationEvent.Success -> {
                    Toast.makeText(context, "it work", Toast.LENGTH_LONG).show()
                    if (userDetails!=null){
                        Toast.makeText(context, userDetails.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                is OTPScreenViewModel.ValidationEvent.ConverUserFromJsonToObject->{
                    if (userDetails!=null)
                    viewModel.onEvent(OTPScreenEvent.GetUserData(userDetails))
                }
            }

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultTopAppBar(
            title = "Verify Phone",
            navigationIcon = Icons.Default.ArrowBack,
            onClick = {
                navController.popBackStack()
            })
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
            Image(painter = painterResource(id = R.drawable.ic_undraw_authentication_re_svpt),
                contentDescription = null,
                modifier = Modifier.matchParentSize())
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Text(
            text = "Code is sent to ${viewModel.userObject.value?.phoneNumber}",
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        OtpTextField(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.xLarge),
            value = state.otpCode,
            onTextChange = {
                viewModel.onEvent(OTPScreenEvent.OtpCodeChanged(it))
            },
            onFill = { viewModel.onEvent(OTPScreenEvent.VirifyCode) }
        )
        if (state.otpCodeErrorMessage != null) {
            Text(
                text = state.otpCodeErrorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.medium)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Didn't receive code ?",
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(
                modifier = Modifier.clickable {

                },
                text = "Request again",
                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primaryVariant)
            )
        }


    }

}