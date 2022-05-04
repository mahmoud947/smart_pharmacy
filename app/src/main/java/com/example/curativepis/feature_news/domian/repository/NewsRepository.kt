package com.example.curativepis.feature_news.domian.repository

import androidx.paging.PagingData
import com.example.curativepis.feature_news.domian.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getAllNews(): Flow<PagingData<Article>>
}