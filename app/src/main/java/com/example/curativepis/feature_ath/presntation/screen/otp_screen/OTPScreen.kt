package com.example.curativepis.feature_ath.presntation.screen.otp_screen

import android.app.Activity
import android.content.Context
import android.util.Log
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
import com.example.curativepis.MainActivity
import com.example.curativepis.R
import com.example.curativepis.core.presentation.components.DefaultTopAppBar
import com.example.curativepis.feature_ath.presntation.screen.otp_screen.components.OtpTextField
import com.example.curativepis.feature_ath.presntation.screen.otp_screen.view_model.OTPScreenViewModel
import com.example.curativepis.ui.theme.spacing
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit

@Composable
fun OTPScreen(
    navController: NavController,
    onNavigate: (String) -> Unit = {},
    viewModel: OTPScreenViewModel = hiltViewModel(),
    phone:String?,
    activite:Activity
) {
    val context = LocalContext.current
    val state = viewModel.uiState.value


    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is OTPScreenViewModel.ValidationEvent.Success -> {

                }
                is OTPScreenViewModel.ValidationEvent.ConverUserFromJsonToObject->{
                    if (phone!=null){
                        send(phone, context = context)
                    }
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
            text = "Code is sent to $phone",
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        OtpTextField(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.xLarge),
            value = state.otpCode,
            onTextChange = {
                viewModel.onEvent(OTPScreenEvent.OtpCodeChanged(it))
            },
            onFill = {
                otpVerification(otp = it, context = context)
            }
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
val turnOffPhoneVerify = FirebaseAuth.getInstance().firebaseAuthSettings
    .setAppVerificationDisabledForTesting(false)
private val mAuth = FirebaseAuth.getInstance()
var verificationOtp = ""

private fun send(mobileNum: String,context: Context) {
    val options = PhoneAuthOptions.newBuilder(mAuth)
        .setPhoneNumber("+20$mobileNum")
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(context as Activity)
        .setCallbacks(object :
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Toast.makeText(context,
                    "Verification Completed",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(context,
                    "Verification Failed",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(
                otp: String,
                p1: PhoneAuthProvider.ForceResendingToken,
            ) {
                super.onCodeSent(otp, p1)
                verificationOtp = otp
                Log.d("ver", verificationOtp)
                Toast.makeText(context,
                    "Otp Send Successfully",
                    Toast.LENGTH_SHORT).show()
            }
        }).build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}

private fun otpVerification(otp: String,context: Context) {
    val credential = PhoneAuthProvider.getCredential(verificationOtp, otp)
    FirebaseAuth.getInstance().signInWithCredential(credential)
        .addOnCompleteListener(context as Activity) { task ->
            if (task.isSuccessful) {
                Toast.makeText(context,
                    "Verification Successful",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Wrong Otp", Toast.LENGTH_SHORT).show()
            }
        }
}