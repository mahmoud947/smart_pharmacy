package com.example.curativepis.feature_news.data.remote

import com.example.curativepis.core.commn.Constants
import com.example.curativepis.feature_news.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsApi {
    @GET()
    suspend fun getAllNews(
        @Url
        url: String = Constants.NEWS_BASE_URL + "top-headlines",
        @Query("page")
        page: Int,
        @Query("pageSize")
        pageSize: Int = 10,
        @Query("category")
        category: String = "health",
        @Query("apiKey")
        apiKey: String = Constants.NEWS_API_KEY,
        ):NewsResponseDto
}