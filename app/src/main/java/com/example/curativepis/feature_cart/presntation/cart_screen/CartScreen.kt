package com.example.curativepis.feature_cart.presntation.cart_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.curativepis.core.presentation.components.ButtonWithElevation
import com.example.curativepis.core.presentation.components.DefaultTopAppBar
import com.example.curativepis.core.presentation.components.ErrorView
import com.example.curativepis.core.presentation.components.LoadingView
import com.example.curativepis.feature_cart.presntation.cart_screen.components.CartItemCard
import com.example.curativepis.feature_cart.presntation.cart_screen.view_model.CartViewModel
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
) {
    val state = viewModel.uiState.value
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(CartScreenEvent.GetCart)
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
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }, title = "Cart")

        Box(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth(),
        ) {
            SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshing),
                onRefresh = {
                    refreshing = true
                    viewModel.onEvent(CartScreenEvent.GetCart)
                },
                indicatorPadding = PaddingValues(top = MaterialTheme.spacing.toolbarHeight)
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(top = MaterialTheme.spacing.large),
                ) {
                    items(items = state.item.items) { item ->
                        CartItemCard(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.small),
                            cartItem = item,
                            onDleteIconClicked = {
                                viewModel.onEvent(event = CartScreenEvent.DeleteCartItem(itemId = item.drugId))

                                viewModel.onEvent(event = CartScreenEvent.GetCart)

                            },
                            onClick = {

                            }
                        )
                    }

                    when {

                        state.isLoading && state.item.items.isEmpty() -> {
                            item {
                                LoadingView(modifier = Modifier.fillParentMaxSize())
                            }
                        }
                        !state.error.isNullOrEmpty() && state.item.items.isEmpty() -> {
                            item {
                                ErrorView(onClickRetry = {
                                    viewModel.onEvent(CartScreenEvent.GetCart)
                                },
                                    message = state.error,
                                    modifier = Modifier.fillParentMaxSize())
                            }
                        }
                    }
                }

            }
        }

        ButtonWithElevation(
            onClick = {
                viewModel.onEvent(CartScreenEvent.PurchaseCart)
                viewModel.onEvent(event = CartScreenEvent.GetCart)
            },
            text = "Buy",
            modifier = Modifier
                .weight(1f, fill = false)
                .padding(horizontal = MaterialTheme.spacing.small)
                .height(MaterialTheme.spacing.largeButtonH)
                .width(MaterialTheme.spacing.largeButtonX),
            isEnable = state.item.items.isNotEmpty()
        )

    }


}