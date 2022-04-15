package com.example.curativepis.feature_news.domain.model

import com.example.curativepis.feature_news.data.remote.dto.SourceDto

data class Article(
    val title: String,
    val author: String?,
    val content: String?,
    val publishedAt: String,
    val url: String,
    val urlToImage: String?
)
