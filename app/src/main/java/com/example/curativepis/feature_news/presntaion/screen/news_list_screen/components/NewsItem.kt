package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.R
import com.example.curativepis.feature_news.domain.model.Article
import com.example.curativepis.ui.theme.spacing

@ExperimentalCoilApi
@Composable
fun NewsItem(
    article: Article,
) {
    if (!article.urlToImage.isNullOrBlank()){

        val imagePainter= rememberImagePainter(data = article.urlToImage)
        NewsCardWithImage(
            imagePainter =  imagePainter,
            titleOverImage =article.title ,
            articleAuthor = article.author,
            articleContent = article.content,
            articlePublishedAt = article.publishedAt)
    }else{
        NewsCardWithOutImage(
            titleOverImage =article.title ,
            articleAuthor = article.author,
            articleContent = article.content,
            articlePublishedAt = article.publishedAt )
    }


}