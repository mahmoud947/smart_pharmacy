package com.example.curativepis.feature_news.presntaion.screen.news_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components.NewsListContent
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.view_model.NewsViewModel
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewsScreen(
    homeViewModel: NewsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
) {

    val getAllNews = homeViewModel.state.value.news.collectAsLazyPagingItems()

    val toolbarHeight = MaterialTheme.spacing.toolbarHeight
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    val scop = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = MaterialTheme.spacing.bottomNavigationBar)
        ) {
            var refreshing by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = refreshing) {
                if (refreshing) {
                    delay(2000)
                    refreshing = false
                }
            }
            Box(modifier = Modifier
                .weight(10f)
                .fillMaxWidth()) {
                SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshing),
                    onRefresh = {
                        refreshing = true
                        getAllNews.refresh()
                    },
                    indicatorPadding= PaddingValues(top = MaterialTheme.spacing.toolbarHeight)


                ) {

                    NewsListContent(items = getAllNews, modifier = Modifier.fillMaxSize())
                }
            }

        }



        TopAppBar(
            modifier = Modifier
                .height(toolbarHeight)
                .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            title = { Text("News") },
            elevation = MaterialTheme.spacing.regulator,
            navigationIcon = {
                IconButton(
                    onClick = {
                        scop.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                ) {
                    Icon(imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu Icon")

                }
            }

        )

    }

}
