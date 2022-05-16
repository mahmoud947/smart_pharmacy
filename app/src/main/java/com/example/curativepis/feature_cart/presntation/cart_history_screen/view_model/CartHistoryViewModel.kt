package com.example.curativepis.feature_cart.presntation.cart_history_screen.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_cart.domian.use_case.CartUseCase
import com.example.curativepis.feature_cart.presntation.cart_history_screen.CartHistoryScreenEvent
import com.example.curativepis.feature_cart.presntation.cart_history_screen.CartHistoryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartHistoryViewModel @Inject constructor(
    private val useCase: CartUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(CartHistoryScreenState())
    fun onEvent(event: CartHistoryScreenEvent) {
        when (event) {
            is CartHistoryScreenEvent.GetCartHistory -> {
                useCase.getUserToken()?.addOnSuccessListener {

                    useCase.getCartHistorytUseCase(token = it.token.toString()).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                uiState = result.data?.let { it1 ->
                                    uiState.copy(
                                        item = it1,
                                        isLoading = false,
                                    )
                                }!!
                            }
                            is Resource.Error -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = result.message.toString()
                                )
                            }
                            is Resource.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true,
                                )
                            }
                        }

                    }.launchIn(viewModelScope)
                }

            }

        }
    }

}