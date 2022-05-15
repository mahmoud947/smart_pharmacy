package com.example.curativepis.feature_auth.presntation.screen.signup_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.presentation.PasswordTextFieldState
import com.example.curativepis.core.presentation.StandardTextFieldState
import com.example.curativepis.feature_auth.domian.use_case.AuthUseCase
import com.example.curativepis.feature_auth.presntation.screen.signup_screen.SignUpScreenEvent
import com.example.curativepis.feature_auth.presntation.screen.signup_screen.SignUpScreenState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val useCase: AuthUseCase,
    private val gson: Gson
) : ViewModel() {

    var uiState by mutableStateOf(SignUpScreenState())





    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.EmailChanged->{
                uiState=uiState.copy(
                    email = event.email

                )
            }
            is SignUpScreenEvent.PhoneChanged->{
                uiState=uiState.copy(
                    phone = event.phone
                )
            }
            is SignUpScreenEvent.PasswordChanged->{
                uiState=uiState.copy(
                    password = event.password
                )
            }
            is SignUpScreenEvent.ConfirmPasswordChanged->{
                uiState=uiState.copy(
                    confirmPassword = event.confirmPassword
                )
            }
            is SignUpScreenEvent.BirthDateChanged->{
                uiState=uiState.copy(
                    dateOfBirth = event.dateOfBirth
                )
            }
            is SignUpScreenEvent.GenderChanged->{
                uiState=uiState.copy(
                    isMale = event.isMale
                )
            }
            is SignUpScreenEvent.UsernameChanged->{
                uiState=uiState.copy(
                    username = event.username
                )
            }

            is SignUpScreenEvent.SignUp->{
                submitData()
            }
            is SignUpScreenEvent.OnNavigate->{

            }

            else -> {}
        }
    }

    private fun submitData() {
        val validUsernameUseCase=useCase.validUsernameUseCase(uiState.username)
        val validatEmailUseCase = useCase.validEmailUseCase(uiState.email)
        val validatPhonelUseCase = useCase.validPhoneUseCase(uiState.phone)
        val validatPasswordUseCase = useCase.validPasswordUseCase(uiState.password)
        val validatConfirmPasswordUseCase = useCase.validConfirmPasswordUseCase(uiState.password,uiState.confirmPassword)

        val hasError = listOf(
            validUsernameUseCase,
            validatEmailUseCase,
            validatPasswordUseCase,
            validatPhonelUseCase,
            validatConfirmPasswordUseCase,
        ).any {
            !it.isValid
        }
        if (hasError) {
            uiState = uiState.copy(
                usernameErrorMessage = validUsernameUseCase.errorMessage,
                emailErrorMessage = validatEmailUseCase.errorMessage,
                phoneErrorMessage = validatPasswordUseCase.errorMessage,
                passwordErrorMessage = validatPasswordUseCase.errorMessage,
                confirmPasswordErrorMessage = validatConfirmPasswordUseCase.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}


