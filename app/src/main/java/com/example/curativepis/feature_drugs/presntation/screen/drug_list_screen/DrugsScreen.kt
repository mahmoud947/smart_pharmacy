package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components.DrugsListContent
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.view_model.DrugsViewModel
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DrugScreen(
    viewModel: DrugsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
) {

    val toolbarHeight = 48.dp
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
                    delay(1000)
                    refreshing = false
                }
            }
            Spacer(modifier = Modifier.offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) })
            Box(
                modifier = Modifier
                    .weight(10f)
                    .fillMaxWidth(),
            ) {
                SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshing),
                    modifier = Modifier.padding(top = MaterialTheme.spacing.bottomNavigationBar),
                    onRefresh = {
                        refreshing = true
                       viewModel.resetItems()
                    }

                ) {

                    DrugsListContent( modifier = Modifier.fillMaxSize(), viewModel = viewModel)
                }
            }

        }
        TopAppBar(
            modifier = Modifier
                .height(toolbarHeight)
                .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            title = { Text("Drugs") },
            actions = {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(shape = MaterialTheme.shapes.large)
                    .clickable {

                    }
                    .background(MaterialTheme.colors.primaryVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Search .....",
                        color = MaterialTheme.colors.background.copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = MaterialTheme.spacing.large,
                                top = MaterialTheme.spacing.small,
                                bottom = MaterialTheme.spacing.small)
                            .weight(4f),
                        textAlign = TextAlign.Start
                    )
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon", modifier = Modifier.weight(1f) )
                    }

                }
            },

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

@Composable
@Preview
fun NewsScreenPreview() {

}