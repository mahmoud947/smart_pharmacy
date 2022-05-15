package com.example.curativepis.feature_drugs.presntation.screen.drug_detail.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_ath.presntation.screen.otp_screen.view_model.OTPScreenViewModel
import com.example.curativepis.feature_drugs.data.remote.request.AddItemToCartReq
import com.example.curativepis.feature_drugs.domian.use_case.DrugsUseCase
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetailState
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetaillScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugDetailViewModel @Inject constructor(
    private val useCase: DrugsUseCase,
) : ViewModel() {
    private val _uiState = mutableStateOf(DrugDetailState())
    val uiState: State<DrugDetailState> = _uiState


    fun onEvent(event: DrugDetaillScreenEvent) {
        when (event) {
            is DrugDetaillScreenEvent.AddItemToCart -> {
                addItemToCart(drugID = event.drugId, quantity = event.quantity)
            }
        }
    }


    private fun addItemToCart(drugID: String,quantity:Int){
        viewModelScope.launch {
            useCase.drugsGetUserTokenUseCase()?.addOnSuccessListener {
                val addItemToCartReq = AddItemToCartReq(drugId = drugID, quantity = quantity)
                useCase.addItemToCartUseCase(
                    token = it.token.toString(),
                    addItemToCartReq = addItemToCartReq
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _uiState.value =_uiState.value.copy(
                                isItemAddToCart = true,
                                itemAddMessage = "item added successfully"
                            )
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isItemAddToCart = false,
                                itemAddMessage = result.message
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }


    fun getDrugDetail(drugID: String) {
        useCase.getDrugByIdUseCase(drugId = drugID).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(
                        isLoading = false,
                        drug = result.data
                    )
                }
                is Resource.Error -> {
                    _uiState.value = uiState.value.copy(
                        error = result.message
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