package com.example.curativepis.feature_news.data.mapper

import com.example.curativepis.feature_news.data.remote.dto.ArticleDto
import com.example.curativepis.feature_news.domian.model.Article

fun ArticleDto.toArticle(): Article =
    Article(
        title = this.title,
        content = this.content,
        author = this.author,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt
    )