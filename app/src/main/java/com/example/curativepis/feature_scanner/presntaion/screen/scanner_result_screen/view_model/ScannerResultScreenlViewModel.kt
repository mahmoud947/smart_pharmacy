package com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_drugs.data.remote.request.AddItemToCartReq
import com.example.curativepis.feature_drugs.domian.use_case.DrugsUseCase
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetailState
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetaillScreenEvent
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen.ScannerResultScreenEvent
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen.ScannerResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScannerResultScreenlViewModel @Inject constructor(
    private val useCase: DrugsUseCase,
) : ViewModel() {
    private val _uiState = mutableStateOf(ScannerResultState())
    val uiState: State<ScannerResultState> = _uiState


    fun onEvent(event: ScannerResultScreenEvent) {
        when (event) {
            is ScannerResultScreenEvent.AddItemToCart -> {
                addItemToCart(drugID = event.drugId)
            }
        }
    }


    private fun addItemToCart(drugID: String,quantity:Int=1){
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


}