package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components.CustomTopAppBar
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components.DrugsListContent
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.view_model.DrugsViewModel
import com.example.curativepis.feature_drugs.presntation.util.DrugsScreens
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
    navController: NavController
) {

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

                    onRefresh = {
                        refreshing = true
                       viewModel.resetItems()
                    }

                ) {

                    DrugsListContent( modifier = Modifier.fillMaxSize(), viewModel = viewModel, navController = navController)
                }
            }

        }
        CustomTopAppBar(
            toolbarHeight = toolbarHeight,
            toolbarOffsetHeightPx = toolbarOffsetHeightPx.value,
            menuIconOnClick = {
                scop.launch {
                    scaffoldState.drawerState.open()
                }
            },
            searchBarOnClick = {
               navController.navigate(DrugsScreens.SearchScreen.route)
            }
        )

    }
}

@Composable
@Preview
fun NewsScreenPreview() {

}