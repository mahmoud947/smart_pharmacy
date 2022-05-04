package com.example.curativepis.feature_news.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.curativepis.core.util.network.Constants
import com.example.curativepis.feature_news.data.mapper.toArticle
import com.example.curativepis.feature_news.data.remote.NewsApi
import com.example.curativepis.feature_news.domian.model.Article
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val api: NewsApi,
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentPage = params.key ?: 1
        return try {
            val response = api.getAllNews(pageSize = Constants.NEWS_PAGE_SIZE, page = currentPage)
            val endOfPaginationReached = response.articles.isEmpty()
            if (response.articles.isNotEmpty()) {
                LoadResult.Page(
                    data = response.articles.map { it.toArticle() },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
}