package com.example.curativepis.feature_auth.presntation.screen.login_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.feature_auth.domian.use_case.AuthUseCase
import com.example.curativepis.feature_auth.presntation.screen.login_screen.LoginScreenEvent
import com.example.curativepis.feature_auth.presntation.screen.login_screen.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val useCase: AuthUseCase,
) : ViewModel() {
    private val _uiState = mutableStateOf(LoginScreenState())
    val uiState: State<LoginScreenState> = _uiState

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.UsernameChanged -> {
                _uiState.value = uiState.value.copy(
                    username = event.username
                )
            }
            is LoginScreenEvent.PasswordChanged -> {
                _uiState.value = uiState.value.copy(
                    password = event.password
                )
            }
            is LoginScreenEvent.Login -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val validatUsernameUseCase = useCase.validEmailUseCase(_uiState.value.username)
        val validatPasswordUseCase = useCase.validPasswordUseCase(_uiState.value.password)
        val hasError = listOf(
            validatUsernameUseCase,
            validatPasswordUseCase
        ).any {
            !it.isValid
        }
        if (hasError) {
            _uiState.value = uiState.value.copy(
                usernameErrorMessage = validatUsernameUseCase.errorMessage,
                passwordErrorMessage = validatPasswordUseCase.errorMessage
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


