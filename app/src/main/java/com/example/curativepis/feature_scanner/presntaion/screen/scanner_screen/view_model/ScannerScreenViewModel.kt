package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_scanner.domian.use_case.ScannerUseCase
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreenEvent
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ScannerScreenViewModel @Inject constructor(
   private val useCase:ScannerUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(ScannerScreenState())
    val uiState: State<ScannerScreenState> = _uiState


    fun onEvent(event: ScannerScreenEvent) {
        when (event) {
            is ScannerScreenEvent.PickImageFromGellary -> {
                _uiState.value = uiState.value.copy(
                    imageUri = event.imageUri,
                    isButtonEnable = true
                )
            }
            is ScannerScreenEvent.UploadImage->{
                useCase.uploadImageUseCase(imagePath =event.filePath ).onEach { result->
                    when(result){
                        is Resource.Success->{
                            _uiState.value = uiState.value.copy(
                               isButtonEnable = true,
                                isLoding = false,
                                drugs = result.data
                            )
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








}