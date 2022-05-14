package com.example.curativepis.feature_cart.presntation.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.curativepis.core.presentation.components.DefaultTopAppBar
import com.example.curativepis.core.presentation.components.ErrorView
import com.example.curativepis.core.presentation.components.LoadingView
import com.example.curativepis.feature_cart.presntation.cart_screen.components.CartItemCard
import com.example.curativepis.ui.theme.spacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
) {
    val state = viewModel.uiState.value
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true ){
    viewModel.onEvent(event = CartScreenEvent.GetCart(token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjY5N2Q3ZmI1ZGNkZThjZDA0OGQzYzkxNThiNjIwYjY5MTA1MjJiNGQiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiYm9iIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL3Rlc3QtYWE3ODIiLCJhdWQiOiJ0ZXN0LWFhNzgyIiwiYXV0aF90aW1lIjoxNjUyNDg1MzcxLCJ1c2VyX2lkIjoiSG1lMmRHeUJXVGdQNDBoaGVKYnJTZW9zSGo3MyIsInN1YiI6IkhtZTJkR3lCV1RnUDQwaGhlSmJyU2Vvc0hqNzMiLCJpYXQiOjE2NTI0ODUzNzEsImV4cCI6MTY1MjQ4ODk3MSwiZW1haWwiOiJib2JAZXhhbXBsZS5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicGhvbmVfbnVtYmVyIjoiKzIwMTI4ODc2ODQwNSIsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsicGhvbmUiOlsiKzIwMTI4ODc2ODQwNSJdLCJlbWFpbCI6WyJib2JAZXhhbXBsZS5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.Gp6vXDZmwHcxsvw0H5kMXhO8TanjTnQAlJMAJqdwZyoXXX9E08w2PA5JHbqdrxDAWBf-wWh0jRdzw8k7S9-Qb-Cy0Vq416RNVSYrxSRL--b_4jRSi0oseS355paHbYKfOg5IBZQkg2V-HKDmiXTlF-GSlUR0e9kEBhCNXIiFRJPkJexLnA9B83yMn7rHoCdi8vfp_TR5RgWnEUuHAtEjSNgJd9z0hMdEQtUUO-42Df1f5JXBnpL0y6c53M8IRnMb4h8WsufyCV4ia07lGS5c6IYNiCPJXL2RZ7ap6ie5O4O1--KvrNFWaUTOjWKxvRBTEQk06OReG0ZdDyXPYMmQlw"))

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
        })

        Box(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth(),
        ) {
            SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshing),
                onRefresh = {
                    refreshing = true
                },
                indicatorPadding = PaddingValues(top = MaterialTheme.spacing.toolbarHeight)
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(top = MaterialTheme.spacing.toolbarHeight)
                ) {
                    items(items = state.item.items) { item ->
                        CartItemCard(modifier = Modifier.fillMaxWidth(), cartItem = item)
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
                                    viewModel.onEvent(event = CartScreenEvent.GetCart(token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjY5N2Q3ZmI1ZGNkZThjZDA0OGQzYzkxNThiNjIwYjY5MTA1MjJiNGQiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiYm9iIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL3Rlc3QtYWE3ODIiLCJhdWQiOiJ0ZXN0LWFhNzgyIiwiYXV0aF90aW1lIjoxNjUyNDg1MzcxLCJ1c2VyX2lkIjoiSG1lMmRHeUJXVGdQNDBoaGVKYnJTZW9zSGo3MyIsInN1YiI6IkhtZTJkR3lCV1RnUDQwaGhlSmJyU2Vvc0hqNzMiLCJpYXQiOjE2NTI0ODUzNzEsImV4cCI6MTY1MjQ4ODk3MSwiZW1haWwiOiJib2JAZXhhbXBsZS5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicGhvbmVfbnVtYmVyIjoiKzIwMTI4ODc2ODQwNSIsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsicGhvbmUiOlsiKzIwMTI4ODc2ODQwNSJdLCJlbWFpbCI6WyJib2JAZXhhbXBsZS5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.Gp6vXDZmwHcxsvw0H5kMXhO8TanjTnQAlJMAJqdwZyoXXX9E08w2PA5JHbqdrxDAWBf-wWh0jRdzw8k7S9-Qb-Cy0Vq416RNVSYrxSRL--b_4jRSi0oseS355paHbYKfOg5IBZQkg2V-HKDmiXTlF-GSlUR0e9kEBhCNXIiFRJPkJexLnA9B83yMn7rHoCdi8vfp_TR5RgWnEUuHAtEjSNgJd9z0hMdEQtUUO-42Df1f5JXBnpL0y6c53M8IRnMb4h8WsufyCV4ia07lGS5c6IYNiCPJXL2RZ7ap6ie5O4O1--KvrNFWaUTOjWKxvRBTEQk06OReG0ZdDyXPYMmQlw"))
                                },
                                    message = state.error,
                                    modifier = Modifier.fillParentMaxSize())
                            }
                        }
                    }
                }


            }
        }

    }
}