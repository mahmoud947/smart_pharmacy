package com.example.curativepis.feature_auth.presntation.screen.splash_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.feature_auth.domian.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val useCase: AuthUseCase,
) : ViewModel() {

    private val _actionEventChannel = Channel<ActionEvent>()
    val actionEventChannel = _actionEventChannel.receiveAsFlow()

    init {
        navigate()
    }

    private fun navigate() {
        if (useCase.getFirebaseCurrentUser()!=null){
                viewModelScope.launch {
                    _actionEventChannel.send(ActionEvent.NavigateToHome)
                }

        }else{
            viewModelScope.launch {
                _actionEventChannel.send(ActionEvent.NavigateToLogin)
            }
        }
    }


    sealed class ActionEvent {
        object NavigateToHome : ActionEvent()
        object NavigateToLogin : ActionEvent()
    }

}