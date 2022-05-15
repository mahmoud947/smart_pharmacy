package com.example.curativepis.feature_cart.presntation.cart_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_cart.data.remote.request.DeleteCartItemRequest
import com.example.curativepis.feature_cart.domian.use_case.CartUseCase
import com.example.curativepis.feature_cart.presntation.cart_screen.CartScreenEvent
import com.example.curativepis.feature_cart.presntation.cart_screen.CartScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCase: CartUseCase,
) : ViewModel() {
    private val _uiState = mutableStateOf(CartScreenState())
    val uiState: State<CartScreenState> = _uiState

    fun onEvent(event: CartScreenEvent) {
        when (event) {
            is CartScreenEvent.GetCart -> {
                useCase.getUserToken()?.addOnSuccessListener {

                    useCase.getCartUseCase(token = it.token.toString()).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                _uiState.value = result.data?.let {
                                    uiState.value.copy(
                                        isLoading = false,
                                        item = it
                                    )
                                } ?: _uiState.value
                            }
                            is Resource.Error -> {
                                _uiState.value = uiState.value.copy(
                                    error = result.message,
                                    isLoading = false
                                )
                            }
                            is Resource.Loading -> {
                                _uiState.value = uiState.value.copy(
                                    isLoading = true
                                )
                            }
                        }

                    }.launchIn(viewModelScope)
                }

            }
            is CartScreenEvent.DeleteCartItem -> {
                useCase.getUserToken()?.addOnSuccessListener {

                    useCase.deleteCartItemUseCase(deleteCartItemId = event.itemId,
                        token = it.token.toString()).onEach {result->
                        when(result){
                            is Resource.Success -> {
                                _uiState.value = result.data?.let {
                                    uiState.value.copy(
                                        isItemDeleted = true,
                                    )
                                } ?: _uiState.value
                            }
                            is Resource.Error -> {
                                _uiState.value = uiState.value.copy(
                                    isItemDeleted = false,
                                    itemDeletedErrorMessage = result.message
                                )
                            }
                            is Resource.Loading -> {
                                //TODO:Maby i will do same thing latter but now i don't know what shoud i do in this case
                            }
                        }

                    }.launchIn(viewModelScope)
                }
            }
        }
    }

}