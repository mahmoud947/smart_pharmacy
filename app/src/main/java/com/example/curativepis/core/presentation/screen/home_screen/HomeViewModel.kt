package com.example.curativepis.core.presentation.screen.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OPenNote->{
                _state.value=state.value.copy(
                    isDrawerOpen = !state.value.isDrawerOpen
                )
            }
        }
    }

}