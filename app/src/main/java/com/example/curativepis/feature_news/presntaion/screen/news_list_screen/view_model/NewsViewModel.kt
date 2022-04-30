package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.curativepis.feature_news.domain.repository.NewsRepository
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.NewsListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
):ViewModel() {

    private val _state= MutableStateFlow(NewsListScreenState())
    val state: StateFlow<NewsListScreenState> = _state

    init {
        getAllNews()
    }
    private fun getAllNews(){
        viewModelScope.launch {
            repository.getAllNews().cachedIn(viewModelScope).collect{
                _state.value=state.value.copy(
                    news = flow { emit(it) }
                )
            }
        }
    }
}