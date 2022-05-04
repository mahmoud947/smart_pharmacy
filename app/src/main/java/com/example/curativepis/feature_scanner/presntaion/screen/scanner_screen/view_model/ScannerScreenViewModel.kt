package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreenEvent
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.ScannerScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScannerScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(ScannerScreenState())
    val state: State<ScannerScreenState> = _state


    fun onEvent(event: ScannerScreenEvent) {
        when (event) {
            is ScannerScreenEvent.SelectImageFromGellary -> {
                _state.value = state.value.copy(
                    isImagePackedFromGallery = true,
                    imageUri = event.imageUri
                )
            }
        }
    }

}