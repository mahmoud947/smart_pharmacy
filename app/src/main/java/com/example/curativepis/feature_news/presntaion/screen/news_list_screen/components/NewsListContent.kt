package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.example.curativepis.feature_news.domain.model.Article
import com.example.curativepis.ui.theme.spacing

@OptIn(ExperimentalCoilApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun NewsListContent(items: LazyPagingItems<Article>) {
    Log.d("Error", items.loadState.toString())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.default),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small.plus(2.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(
            items = items,
            key = { article ->
                article.url
            }
        ) { article ->
            article?.let {
                NewsItem(article = it)
            }
        }

    }
}
