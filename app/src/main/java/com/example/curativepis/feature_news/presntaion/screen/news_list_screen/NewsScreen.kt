package com.example.curativepis.feature_news.presntaion.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.NewsViewModel
import com.example.curativepis.feature_news.presntaion.screen.news_list_screen.components.NewsListContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewsScreen(
    homeViewModel: NewsViewModel = hiltViewModel()
) {
    val scaffoldState= rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope= rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                title = {
                    Row(modifier = Modifier.fillMaxWidth().padding(end = 72.dp), horizontalArrangement = Arrangement.Center) {
                        Text(text = "News")
                    }

                        },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                  scaffoldState.drawerState.open()
                            }
                        },
                    ) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")

                    }
                }
            )
        },
        drawerContent = {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
        }
    ) {
        val systemUiController = rememberSystemUiController()
        val getAllNews=homeViewModel.state.value.news.collectAsLazyPagingItems()
        val color=MaterialTheme.colors.primary
        SideEffect {
            systemUiController.setStatusBarColor(
                color = color,
                darkIcons = false
            )
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            contentAlignment = Alignment.Center
        ) {


            NewsListContent(items = getAllNews)

        }
        
    }
    
}

@Composable
@Preview
fun NewsScreenPreview() {
    NewsScreen()
}