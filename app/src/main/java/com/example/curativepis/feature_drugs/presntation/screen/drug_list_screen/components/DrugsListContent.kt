package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import com.example.curativepis.core.presentation.screen.main_screen.components.ErrorView
import com.example.curativepis.core.presentation.screen.main_screen.components.LoadingView
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.view_model.DrugsViewModel
import com.example.curativepis.ui.theme.spacing


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrugsListContent(
    modifier: Modifier = Modifier,
    viewModel: DrugsViewModel,
) {

    val state = viewModel.uiState
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.bottomNavigationBar),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("list",state.item.toString())

        val itemCount = if (state.item.size % 3 == 0) {
            state.item.size / 3
        } else {
            state.item.size / 3 + 1
        }

        items(itemCount) { index ->
            if (index >= itemCount - 1 && !state.endReached && !state.isLoading) {
                viewModel.loadNextItems()
            }
                DrugRowItem(rowIndex = index, drugs =state.item )
            Spacer(modifier = Modifier.height(12.dp))

        }



        when {
            state.isLoading && state.item.isNotEmpty() -> {
                item {
                    LoadingView(modifier = Modifier.fillMaxWidth())
                }
            }
            !state.error.isNullOrEmpty() && state.item.isNotEmpty() -> {
                item {
                    ErrorView(onClickRetry = { viewModel.loadNextItems() },
                        message = state.error,
                        modifier = Modifier.fillMaxSize())
                }
            }
            state.isLoading && state.item.isEmpty() -> {
                item {
                    LoadingView(modifier = Modifier.fillParentMaxSize())
                }
            }
            !state.error.isNullOrEmpty() && state.item.isEmpty() -> {
                item {
                    ErrorView(onClickRetry = { viewModel.loadNextItems() },
                        message = state.error,
                        modifier = Modifier.fillParentMaxSize())
                }
            }
        }

    }


}



