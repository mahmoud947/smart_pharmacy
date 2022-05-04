package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.example.curativepis.core.presentation.screen.main_screen.components.ErrorView
import com.example.curativepis.core.presentation.screen.main_screen.components.LoadingView
import com.example.curativepis.feature_news.domian.model.Article
import com.example.curativepis.ui.theme.spacing

@OptIn(ExperimentalCoilApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun NewsListContent(
    items: LazyPagingItems<Article>,
    modifier: Modifier = Modifier,
) {
    Log.d("Error", items.loadState.toString())
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 60.dp),
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

        items.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingView() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = items.loadState.refresh as LoadState.Error
                    item {
                        ErrorView(
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() },
                            message = "No internet connection"
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        ErrorView(
                            onClickRetry = {
                                retry()
                                Log.d("click", "clicked")
                            },
                            message = null
                        )
                    }
                }

            }
        }
    }
}
