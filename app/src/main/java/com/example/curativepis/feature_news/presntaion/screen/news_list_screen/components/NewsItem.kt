package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.curativepis.feature_news.domain.model.Article

@Composable
fun NewsItem(
    article: Article,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            val cutText = article.content?.substring(startIndex = 0, endIndex = 150)
            if (cutText != null) {
                ExpandedText(text = cutText, expandedText = "See more", expandedTextButton = "See more", shrinkTextButton ="See less" )
            }

        }
    }

}