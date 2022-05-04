package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.feature_news.domian.model.Article

@ExperimentalCoilApi
@Composable
fun NewsItem(
    article: Article,
) {
    val context = LocalContext.current
    if (!article.urlToImage.isNullOrBlank()){

        val imagePainter= rememberImagePainter(data = article.urlToImage)
        NewsCardWithImage(
            imagePainter =  imagePainter,
            titleOverImage =article.title ,
            articleAuthor = article.author,
            articleContent = article.content,
            articlePublishedAt = article.publishedAt,
        onClick = {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(article.url)
            )
            startActivity(context, browserIntent, null)
        })

    }else{
        NewsCardWithOutImage(
            titleOverImage =article.title ,
            articleAuthor = article.author,
            articleContent = article.content,
            articlePublishedAt = article.publishedAt ,
            onClick = {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(article.url)
                )
                startActivity(context, browserIntent, null)
            })
    }


}