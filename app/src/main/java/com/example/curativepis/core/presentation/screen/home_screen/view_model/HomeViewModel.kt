package com.example.curativepis.core.presentation.screen.home_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.curativepis.core.domian.use_case.SignOutCurrentUserUseCase
import com.example.curativepis.core.presentation.screen.home_screen.HomeScreenEvent
import com.example.curativepis.core.presentation.screen.home_screen.HomeScreenState
import com.example.curativepis.feature_auth.domian.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val signOutCurrentUserUseCase: SignOutCurrentUserUseCase,
   private val useCase: AuthUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state
    var drowerState by mutableStateOf(DrowerContent())



init {
    fillDrower()
}


private fun fillDrower(){
   val user= useCase.getCurrentUserFromLocalUseCase()
    drowerState=drowerState.copy(
        username = user.username,
        email = user.email,
        phone = user.phoneNumber
    )
}


    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OPenNote ->{
                _state.value=state.value.copy(
                    isDrawerOpen = !state.value.isDrawerOpen
                )
            }
            is HomeScreenEvent.SignOut->{
                signOutCurrentUserUseCase()
                useCase.removeCurrenFromLocalUserUseCase()
            }
        }
    }

}

data class DrowerContent(
    val username:String="",
    val email:String="",
    val phone:String=""
)