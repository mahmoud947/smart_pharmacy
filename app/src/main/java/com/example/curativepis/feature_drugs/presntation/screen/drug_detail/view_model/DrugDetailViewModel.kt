package com.example.curativepis.feature_drugs.presntation.screen.drug_detail.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_drugs.domian.use_case.DrugsUseCase
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DrugDetailViewModel @Inject constructor(
  private val useCase: DrugsUseCase
) : ViewModel(){
    private val _uiState= mutableStateOf(DrugDetailState())
    val uiState:State<DrugDetailState> = _uiState



    fun getDrugDetail(drugID:String){
        useCase.getDrugByIdUseCase(drugId = drugID).onEach { result->
            when(result){
                is Resource.Success->{
                    _uiState.value=uiState.value.copy(
                        isLoading = false,
                        drug = result.data
                    )
                }
                is Resource.Error->{
                    _uiState.value=uiState.value.copy(
                       error = result.message
                    )
                }
                is Resource.Loading->{
                    _uiState.value=uiState.value.copy(
                       isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}