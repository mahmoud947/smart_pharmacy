package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.RunTimeCash
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_auth.presntation.screen.login_screen.view_model.LoginScreenViewModel
import com.example.curativepis.feature_scanner.domian.use_case.ScannerUseCase
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreenEvent
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScannerScreenViewModel @Inject constructor(
   private val useCase:ScannerUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(ScannerScreenState())
    val uiState: State<ScannerScreenState> = _uiState
    var scannerResult by mutableStateOf(ScannerScreenState())

    private val validationEventChannel = Channel<ScannerScreenViewModel.ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: ScannerScreenEvent) {
        when (event) {
            is ScannerScreenEvent.PickImageFromGellary -> {
                _uiState.value = uiState.value.copy(
                    imageUri = event.imageUri,
                    isButtonEnable = true
                )
            }
            is ScannerScreenEvent.UploadImage->{
                useCase.uploadImageToScannerServiceUseCase(imagePath =event.filePath ).onEach { result->
                    when(result){
                        is Resource.Success->{
                            _uiState.value = uiState.value.copy(
                               isButtonEnable = true,
                                isLoding = false,
                                scannerresponse = result.data?: emptyList()
                            )
                            scannerResult=scannerResult.copy(
                                isButtonEnable = true,
                                isLoding = false,
                                scannerresponse = result.data?: emptyList()
                            )
                            RunTimeCash.arr= result.data!!
                            navigate()
                        }
                        is Resource.Error->{
                            _uiState.value = uiState.value.copy(
                                error = result.message
                            )
                        }
                        is Resource.Loading->{
                            _uiState.value = uiState.value.copy(
                                isButtonEnable = false,
                                isLoding = true
                            )
                        }
                    }

                }.launchIn(viewModelScope)
            }
        }
    }
    private fun navigate(){
        viewModelScope.launch {
            delay(2000)
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }

}