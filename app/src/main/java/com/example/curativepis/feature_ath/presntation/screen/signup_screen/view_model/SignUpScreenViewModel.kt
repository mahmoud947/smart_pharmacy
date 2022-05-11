package com.example.curativepis.feature_ath.presntation.screen.signup_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.feature_ath.domian.use_case.AuthUseCase
import com.example.curativepis.feature_ath.presntation.screen.signup_screen.SignUpScreenEvent
import com.example.curativepis.feature_ath.presntation.screen.signup_screen.SignUpScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val useCase: AuthUseCase,
) : ViewModel() {
    private val _uiState = mutableStateOf(SignUpScreenState())
    val uiState: State<SignUpScreenState> = _uiState

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.EmailChanged->{
                _uiState.value=uiState.value.copy(
                    email = event.email,
                )
            }
            is SignUpScreenEvent.PhoneChanged->{
                _uiState.value=uiState.value.copy(
                    phone = event.phone,
                )
            }
            is SignUpScreenEvent.PasswordChanged->{
                _uiState.value=uiState.value.copy(
                    password = event.password,
                )
            }
            is SignUpScreenEvent.ConfirmPasswordChanged->{
                _uiState.value=uiState.value.copy(
                    confirmPassword = event.confirmPassword,
                )
            }
            is SignUpScreenEvent.BirthDateChanged->{
                _uiState.value=uiState.value.copy(
                    dateOfBirth = event.dateOfBirth,
                )
            }
            is SignUpScreenEvent.GenderChanged->{
                _uiState.value=uiState.value.copy(
                    isMale = event.isMale,
                )
            }
            is SignUpScreenEvent.SignUp->{
                submitData()
            }
        }
    }

    private fun submitData() {
        val validatEmailUseCase = useCase.validEmailUseCase(_uiState.value.email)
        val validatPhonelUseCase = useCase.validPhoneUseCase(_uiState.value.phone)
        val validatPasswordUseCase = useCase.validPasswordUseCase(_uiState.value.password)
        val validatConfirmPasswordUseCase = useCase.validConfirmPasswordUseCase(_uiState.value.password,_uiState.value.confirmPassword)

        val hasError = listOf(
            validatEmailUseCase,
            validatPasswordUseCase,
            validatPhonelUseCase,
            validatConfirmPasswordUseCase
        ).any {
            !it.isValid
        }
        if (hasError) {
            _uiState.value = uiState.value.copy(
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


