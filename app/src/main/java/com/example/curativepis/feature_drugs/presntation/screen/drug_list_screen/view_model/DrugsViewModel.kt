package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.curativepis.feature_drugs.domain.use_case.DrugsUseCase
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.DrugsListScreenState
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.NewsListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugsViewModel @Inject constructor(
    private val drugsUseCase: DrugsUseCase,
) : ViewModel() {

    private val _state= MutableStateFlow(DrugsListScreenState())
    val state: StateFlow<DrugsListScreenState> = _state


    init {
        getAllDrugs()
    }

    private fun getAllDrugs(){
        viewModelScope.launch {
            drugsUseCase.getDrugsUseCase().cachedIn(viewModelScope).collect{
                _state.value=state.value.copy(
                    drugs = flow { emit(it) }
                )
            }
        }
    }
}