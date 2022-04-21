package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import com.example.curativepis.R
import com.example.curativepis.ui.theme.spacing


@ExperimentalCoilApi
@Composable
fun NewsCardWithImage(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(15.dp),
    cardElevation: Dp = 8.dp,
    imagePainter: ImagePainter,
    borderStroke: BorderStroke = BorderStroke(width = 0.dp, color = MaterialTheme.colors.primary),
    titleOverImage: String,
    articleAuthor: String?,
    articleContent: String?,
    articlePublishedAt: String,
    ) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.small),
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = shape,
            elevation = cardElevation,
            border = borderStroke
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(modifier = Modifier
                    .height(250.dp)
                ) {
                    Image(
                        painter = imagePainter,
                        contentDescription = titleOverImage,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
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
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 21.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
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
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        textAlign = TextAlign.Center
                                    ),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 2)

                            }
                        }


                    }
                }

                Column(
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    if (articleContent != null) {
                        ExpandableText(text = articleContent, minimizedMaxLines = 2,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            textColor = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.spacing.regulator,
                                horizontal = MaterialTheme.spacing.small)
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.news_time_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .padding(start = MaterialTheme.spacing.small,
                                    end = MaterialTheme.spacing.small)
                                .size(16.dp)

                        )

                        Text(
                            text = articlePublishedAt,
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraLight,
                                textAlign = TextAlign.Center
                            ),
                        )

                    }
                }


            }
        }
    }

}