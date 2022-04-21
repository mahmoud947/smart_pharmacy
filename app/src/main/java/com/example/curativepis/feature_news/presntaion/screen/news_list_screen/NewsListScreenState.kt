package com.example.curativepis.feature_news.presntaion.screen.news_list_screen

import androidx.paging.PagingData
import com.example.curativepis.feature_news.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

data class NewsListScreenState(
   val news: Flow<PagingData<Article>> = emptyFlow()
)

