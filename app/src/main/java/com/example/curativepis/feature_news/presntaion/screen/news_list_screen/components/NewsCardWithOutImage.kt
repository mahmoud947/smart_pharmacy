package com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.example.curativepis.R
import com.example.curativepis.ui.theme.appShape
import com.example.curativepis.ui.theme.elevation
import com.example.curativepis.ui.theme.spacing
import com.example.curativepis.ui.theme.white

@ExperimentalCoilApi
@Composable
fun NewsCardWithOutImage(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.appShape.regulator,
    cardElevation: Dp = MaterialTheme.elevation.regulator,
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
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .fillMaxSize()
                .clickable { onClick() },
        ) {

            Box(modifier = Modifier
                .height(80.dp)
            ) {
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
                                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onSurface)
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
                                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onSurface),
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
                            .background(Color.White),
                    )
                }

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.regulator,
                            horizontal = MaterialTheme.spacing.small)
                        .background(MaterialTheme.colors.surface),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier
                            .padding(start = MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.small)
                            .size(16.dp)

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