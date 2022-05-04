package com.example.curativepis.feature_news.domian.model

data class Article(
    val title: String,
    val author: String?,
    val content: String?,
    val publishedAt: String,
    val url: String,
    val urlToImage: String?
)
