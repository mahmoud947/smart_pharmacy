package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import com.example.curativepis.R
import com.example.curativepis.ui.theme.appShape
import com.example.curativepis.ui.theme.elevation
import com.example.curativepis.ui.theme.spacing
import com.example.curativepis.ui.theme.white


@ExperimentalCoilApi
@Composable
fun NewsCardWithImage(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.appShape.regulator,
    cardElevation: Dp = MaterialTheme.elevation.regulator,
    imagePainter: ImagePainter,
    titleOverImage: String,
    articleAuthor: String?,
    articleContent: String?,
    articlePublishedAt: String,
    onClick: () -> Unit,
) {


    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.small),
        shape = shape,
        elevation = cardElevation,
        contentColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            Box(modifier = Modifier
                .height(250.dp)
                .clickable {
                    onClick()
                }
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = titleOverImage,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = Brush.verticalGradient(colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                            startY = 150f
                        ))
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.medium),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {

                        if (articleAuthor != null) {
                            Text(
                                text = articleAuthor,
                                style = MaterialTheme.typography.h6.copy(color = white)
                            )
                        }

                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                        Row(
                            modifier = Modifier.height(MaterialTheme.spacing.large),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Divider(
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(MaterialTheme.spacing.small)
                            )
                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                            Text(
                                text = titleOverImage,
                                style = MaterialTheme.typography.subtitle1.copy(color = white),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2)

                        }
                    }


                }
            }

            Column {
                if (articleContent != null) {
                    ExpandableText(
                        text = articleContent, minimizedMaxLines = 2,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.surface),
                    )
                }

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colors.surface)
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.regulator,
                            horizontal = MaterialTheme.spacing.small),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier
                            .padding(start = MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.small)
                            .size(MaterialTheme.spacing.medium)
                    )

                    Text(
                        text = articlePublishedAt,
                        style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.onSurface)
                    )

                }
            }


        }

    }

}