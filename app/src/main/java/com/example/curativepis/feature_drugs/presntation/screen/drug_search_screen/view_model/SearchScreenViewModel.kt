package com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_drugs.domian.use_case.DrugsUseCase
import com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.DrugsSearchScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val useCase: DrugsUseCase,
) : ViewModel() {

    private val _uiState = mutableStateOf(DrugsSearchScreenState())
    val uiState: State<DrugsSearchScreenState> = _uiState

    // text field
    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    fun setSearchText(drugName: String) {
        _searchText.value = drugName
    }

    fun getDrugsByName() {
        if (_searchText.value.isNotEmpty()){
        useCase.getDrugsByNameUseCase(_searchText.value).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value = uiState.value.copy(
                        isLoading = false,
                        item = result.data ?: emptyList(),
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
        }else{
            _uiState.value=uiState.value.copy(
                item = emptyList()
            )
        }
    }


}