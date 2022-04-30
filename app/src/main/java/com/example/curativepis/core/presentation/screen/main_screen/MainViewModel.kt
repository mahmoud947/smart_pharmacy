package com.example.curativepis.core.presentation.screen.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    fun onEvent(event: MainScreenEvent){
        when(event){
            is MainScreenEvent.OPenNote->{
                _state.value=state.value.copy(
                    isDrawerOpen = !state.value.isDrawerOpen
                )
            }
        }
    }

}