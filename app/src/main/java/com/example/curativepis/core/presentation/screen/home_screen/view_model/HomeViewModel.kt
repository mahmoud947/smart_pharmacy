package com.example.curativepis.core.presentation.screen.home_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.curativepis.core.domian.use_case.SignOutCurrentUserUseCase
import com.example.curativepis.core.presentation.screen.home_screen.HomeScreenEvent
import com.example.curativepis.core.presentation.screen.home_screen.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val signOutCurrentUserUseCase: SignOutCurrentUserUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state







    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OPenNote ->{
                _state.value=state.value.copy(
                    isDrawerOpen = !state.value.isDrawerOpen
                )
            }
            is HomeScreenEvent.SignOut->{
                signOutCurrentUserUseCase()
            }
        }
    }

}