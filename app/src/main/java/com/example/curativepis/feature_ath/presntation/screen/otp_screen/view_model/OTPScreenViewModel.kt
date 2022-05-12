package com.example.curativepis.feature_ath.presntation.screen.otp_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.feature_ath.domian.model.PisUser
import com.example.curativepis.feature_ath.domian.use_case.AuthUseCase
import com.example.curativepis.feature_ath.presntation.screen.otp_screen.OTPScreenEvent
import com.example.curativepis.feature_ath.presntation.screen.otp_screen.OTPScreenState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OTPScreenViewModel @Inject constructor(
    private val useCase: AuthUseCase,
    private val gson: Gson,
) : ViewModel() {
    private val _uiState = mutableStateOf(OTPScreenState())
    val uiState: State<OTPScreenState> = _uiState

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    val userObject = mutableStateOf<PisUser?>(null)

    init {
        viewModelScope.launch {
        validationEventChannel.send(ValidationEvent.ConverUserFromJsonToObject)
        }
    }

    fun onEvent(event: OTPScreenEvent) {
        when (event) {
            is OTPScreenEvent.OtpCodeChanged -> {
                _uiState.value = uiState.value.copy(
                    otpCode = event.code
                )
            }

            is OTPScreenEvent.VirifyCode -> {
                submitData()
            }
            is OTPScreenEvent.GetUserData -> {
                val user = gson.fromJson(event.userAsJson, PisUser::class.java)
                userObject.value=user
            }
        }
    }


    private fun submitData() {
        val validatOTPCodeUseCase = useCase.validOTPCodeUseCase(_uiState.value.otpCode)

        if (!validatOTPCodeUseCase.isValid) {
            _uiState.value = uiState.value.copy(
                otpCodeErrorMessage = validatOTPCodeUseCase.errorMessage
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
        object ConverUserFromJsonToObject : ValidationEvent()
    }
}


