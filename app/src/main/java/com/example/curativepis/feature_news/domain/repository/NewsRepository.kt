package com.example.curativepis.feature_news.domain.repository

import androidx.paging.PagingData
import com.example.curativepis.feature_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getAllNews(): Flow<PagingData<Article>>
}