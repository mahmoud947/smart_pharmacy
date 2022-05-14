package com.example.curativepis.feature_cart.presntation.cart_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_cart.domian.use_case.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
   private val useCase: CartUseCase,
) :ViewModel(){
    private val _uiState= mutableStateOf(CartScreenState())
    val uiState:State<CartScreenState> = _uiState

    fun onEvent(event: CartScreenEvent){
        when(event){
            is CartScreenEvent.GetCart->{
                useCase.getCartUseCase(token = event.token).onEach { result->
                    when(result){
                        is Resource.Success->{
                            _uiState.value = result.data?.let {
                                uiState.value.copy(
                                    isLoading = false,
                                    item = it
                                )
                            }?:_uiState.value
                        }
                        is Resource.Error->{
                            _uiState.value = uiState.value.copy(
                                error = result.message
                            )
                        }
                        is Resource.Loading->{
                            _uiState.value = uiState.value.copy(
                                isLoading = true
                            )
                        }
                    }

                }.launchIn(viewModelScope)
            }
        }
    }

}