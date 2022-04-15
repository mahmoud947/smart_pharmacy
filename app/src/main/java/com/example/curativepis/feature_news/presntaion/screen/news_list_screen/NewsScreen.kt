package com.example.curativepis.feature_news.presntaion.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.NewsViewModel
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components.NewsListContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NewsScreen(
    homeViewModel: NewsViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        val systemUiController = rememberSystemUiController()
        val getAllNews=homeViewModel.state.value.news.collectAsLazyPagingItems()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color(0XFF19D3DA),
                darkIcons = false
            )
        }
        NewsListContent(items = getAllNews)

    }
}

@Composable
@Preview
fun NewsScreenPreview() {
    NewsScreen()
}