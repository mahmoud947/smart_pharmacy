package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curativepis.core.data.paging.DefaultPaginator
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository
import com.example.curativepis.feature_drugs.domian.use_case.DrugsUseCase
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.DrugsListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugsViewModel @Inject constructor(
    private val drugsUseCase: DrugsUseCase,
    private val repository: DrugsRepository,
) : ViewModel() {

    var uiState by mutableStateOf(DrugsListScreenState())

    private val paginator = DefaultPaginator(
        initialKey = uiState.page,
        onLoadUpdated = {
            uiState = uiState.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            drugsUseCase.getDrugsUseCase(nextPage)
        },
        getNextKey = {
            uiState.page + 1
        },
        onError = {
            uiState = uiState.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            uiState = uiState.copy(
                item = uiState.item + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun resetItems() {
        viewModelScope.launch {
            paginator.reset()
            uiState = uiState.copy(
                isLoading = false,
                item = emptyList(),
                error = null,
                endReached = false,
                page = 0,
            )
            viewModelScope.launch {

                paginator.loadNextItems()
            }
        }
    }


}
