package com.example.curativepis.feature_auth.presntation.screen.otp_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_auth.domian.model.PisUser
import com.example.curativepis.feature_auth.domian.use_case.AuthUseCase
import com.example.curativepis.feature_auth.presntation.screen.otp_screen.OTPScreenEvent
import com.example.curativepis.feature_auth.presntation.screen.otp_screen.OTPScreenState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private val _actionEventChannel = Channel<ActionEvent>()
    val actionEvents = _actionEventChannel.receiveAsFlow()

    val userObject = mutableStateOf<PisUser?>(null)

    init {
        viewModelScope.launch {
            _actionEventChannel.send(ActionEvent.ConverUserFromJsonToObject)
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
                val isVerifiy =
                    useCase.verificationOtpUseCase(otp = event.code, activity = event.activity)
                if (isVerifiy) {
                    submitData()
                } else {
                    _uiState.value = uiState.value.copy(
                        otpCodeErrorMessage = "please enter correct code"
                    )
                }
            }
            is OTPScreenEvent.GetUserData -> {
                val user = gson.fromJson(event.userAsJson, PisUser::class.java)
                userObject.value = user
            }
            is OTPScreenEvent.SendOtpMessage -> {
                useCase.sendOtpMessageUseCase(event.phone, activity = event.activity)
            }
            is OTPScreenEvent.SignUp -> {
                useCase.pushNewUserUseCase(userRequestObject = event.userRequestObject, toke = event.token).onEach { result->
                    when(result){
                        is Resource.Success->{
                            useCase.getCurrentUserFromServerSideUseCase(token = event.token).onEach {result2->
                                when(result2){
                                    is Resource.Success->{
                                        result2.data?.let { it3 -> useCase.saveCurrenUserFromLocalUseCase(it3) }
                                    }
                                    is Resource.Error->{

                                    }
                                    else -> {}
                                }
                            }.launchIn(viewModelScope)

                            _uiState.value=uiState.value.copy(
                                isLoading = false,
                                createUserIsError = result.data?.is_error?:false,
                                createUserIsErrorMessage = result.data?.message?:"user created"
                            )
                            onNavigate()
                        }
                        is Resource.Error->{
                            _uiState.value=uiState.value.copy(
                                createUserIsError = result.message!=null,
                                createUserIsErrorMessage = result.message
                            )

                        }
                        is Resource.Loading->{
                            _uiState.value=uiState.value.copy(
                                isLoading = true
                            )

                        }
                    }
                }.launchIn(viewModelScope)
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
            _actionEventChannel.send(ActionEvent.ValidateSuccess)
        }
    }

    private fun onNavigate(){
        viewModelScope.launch {
            _actionEventChannel.send(ActionEvent.Navigate)
        }
    }

    sealed class ActionEvent {
        object ValidateSuccess : ActionEvent()
        object ConverUserFromJsonToObject : ActionEvent()
        object Navigate:ActionEvent()
    }
}


