package com.example.curativepis.feature_cart.presntation.cart_history_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.core.presentation.components.ButtonWithElevation
import com.example.curativepis.core.presentation.components.DefaultTopAppBar
import com.example.curativepis.core.presentation.components.ErrorView
import com.example.curativepis.core.presentation.components.LoadingView
import com.example.curativepis.feature_cart.presntation.cart_history_screen.components.CartHistoryCard
import com.example.curativepis.feature_cart.presntation.cart_history_screen.view_model.CartHistoryViewModel
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartHistoryScreen(
    viewModel: CartHistoryViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.uiState
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        viewModel.onEvent(CartHistoryScreenEvent.GetCartHistory)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        var refreshing by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = refreshing) {
            if (refreshing) {
                delay(1000)
                refreshing = false
            }
        }
        DefaultTopAppBar(onClick = {
            navController.popBackStack()
        }, navigationIcon = Icons.Default.ArrowBack, title = "History")

        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshing),
                onRefresh = {
                    refreshing = true
                    viewModel.onEvent(CartHistoryScreenEvent.GetCartHistory)
                },
                indicatorPadding = PaddingValues(top = MaterialTheme.spacing.toolbarHeight)
            ) {

                Column(modifier = Modifier.fillMaxSize()) {
                    if (state.item.isNotEmpty()) {
                        var cartIndex by remember {
                            mutableStateOf(0)
                        }
                        val cartHistory = state.item[cartIndex]


                        LazyColumn(
                            modifier = Modifier
                                .weight(4f)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            contentPadding = PaddingValues(top = MaterialTheme.spacing.large),
                        ) {
                            items(items = cartHistory.items) { cartItem ->
                                CartHistoryCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = MaterialTheme.spacing.small),
                                    cartHistory = cartHistory,
                                    cartHistoryItem = cartItem,
                                )
                            }


                            when {

                                state.isLoading && state.item.isEmpty() -> {
                                    item {
                                        LoadingView(modifier = Modifier.fillParentMaxSize())
                                    }
                                }
                                !state.error.isNullOrEmpty() && state.item.isEmpty() -> {
                                    item {
                                        ErrorView(onClickRetry = {
                                            viewModel.onEvent(CartHistoryScreenEvent.GetCartHistory)
                                        },
                                            message = state.error,
                                            modifier = Modifier.fillParentMaxSize())
                                    }
                                }
                            }
                        }
                        var expanded by remember { mutableStateOf(false) }
                        var dropDownIndex by remember {
                            mutableStateOf(0)
                        }
                        Box(modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.TopStart)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                Text(
                                    state.item[dropDownIndex].createdAt,
                                    style = MaterialTheme.typography.subtitle1.copy(MaterialTheme.colors.onSurface),
                                    modifier = Modifier
                                        .weight(2f)
                                        .fillMaxWidth()
                                        .clickable(onClick = { expanded = true })
                                )
                                Icon(imageVector = Icons.Default.ArrowDownward,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.onSurface,
                                modifier = Modifier.weight(1f).clickable {  expanded = !expanded })

                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = !expanded },
                                    modifier = Modifier
                                        .weight(2f),
                                ) {
                                    state.item.forEach { cartHistory ->
                                        DropdownMenuItem(onClick = {
                                            dropDownIndex = state.item.indexOf(cartHistory)
                                            expanded = !expanded
                                            cartIndex = state.item.indexOf(cartHistory)
                                        }, modifier = Modifier.fillMaxSize()) {
                                            Text(
                                                text = cartHistory.createdAt,
                                                style = MaterialTheme.typography.subtitle1.copy(
                                                    color = MaterialTheme.colors.onSurface)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }


}