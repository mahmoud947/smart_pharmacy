package com.example.curativepis.feature_auth.presntation.screen.login_screen.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_auth.data.remote.request.GetCustomTokenRequestObject
import com.example.curativepis.feature_auth.domian.use_case.AuthUseCase
import com.example.curativepis.feature_auth.presntation.screen.login_screen.LoginScreenEvent
import com.example.curativepis.feature_auth.presntation.screen.login_screen.LoginScreenState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val useCase: AuthUseCase,
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {
    var uiState by mutableStateOf(LoginScreenState())


    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.UsernameChanged -> {
                uiState = uiState.copy(
                    email = event.username
                )
            }
            is LoginScreenEvent.PasswordChanged -> {
                uiState = uiState.copy(
                    password = event.password
                )
            }
            is LoginScreenEvent.Login -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val validatUsernameUseCase = useCase.validEmailUseCase(uiState.email)
        val validatPasswordUseCase = useCase.validPasswordUseCase(uiState.password)
        val hasError = listOf(
            validatUsernameUseCase,
            validatPasswordUseCase
        ).any {
            !it.isValid
        }
        if (hasError) {
            uiState = uiState.copy(
                emailErrorMessage = validatUsernameUseCase.errorMessage,
                passwordErrorMessage = validatPasswordUseCase.errorMessage
            )
            return
        } else {
            val getCusttomTokenRequestObject =
                GetCustomTokenRequestObject(email = uiState.email.trim(), pass = uiState.password.trim())
            useCase.getCustomTokentUseCase(getCustomTokenRequestObject = getCusttomTokenRequestObject)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            viewModelScope.launch {
                                firebaseAuth.signInWithCustomToken(result.data?.token.toString())
                                    .addOnSuccessListener {
                                        viewModelScope.launch {

                                            validationEventChannel.send(ValidationEvent.Success)
                                        }
                                    }
                            }
                        }
                        is Resource.Loading -> {
                            uiState=uiState.copy(isLoading = true)
                        }
                        is Resource.Error->{
                            uiState=uiState.copy(isLoading = false, isEmailOrPasswordError = true)
                        }
                    }

                }.launchIn(viewModelScope)

        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}


