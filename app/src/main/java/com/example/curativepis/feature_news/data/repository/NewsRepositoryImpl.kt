package com.example.curativepis.feature_news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.curativepis.core.util.network.Constants
import com.example.curativepis.feature_news.data.paging.NewsPagingSource
import com.example.curativepis.feature_news.data.remote.NewsApi
import com.example.curativepis.feature_news.domian.model.Article
import com.example.curativepis.feature_news.domian.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
) : NewsRepository {
    override suspend fun getAllNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.NEWS_PAGE_SIZE),
            pagingSourceFactory = {
                NewsPagingSource(api = api)
            }
        ).flow
    }


}