package com.example.curativepis.feature_news.presntaion.screen.news_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.curativepis.feature_news.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
):ViewModel() {

    private val _state= mutableStateOf(NewsListScreenState())
    val state: State<NewsListScreenState> = _state

    init {
        getAllNews()
    }
    private fun getAllNews(){
        viewModelScope.launch {
            repository.getAllNews().cachedIn(viewModelScope).collect{
                _state.value.news= flow { emit(it) }
            }
        }
    }
}