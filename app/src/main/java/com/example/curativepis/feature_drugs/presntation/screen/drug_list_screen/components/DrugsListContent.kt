package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.curativepis.core.presentation.screen.main_screen.components.ErrorView
import com.example.curativepis.core.presentation.screen.main_screen.components.LoadingView
import com.example.curativepis.feature_drugs.domain.model.Drug


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrugsListContent(
    items: LazyPagingItems<Drug>,
    modifier: Modifier = Modifier,
) {

    Log.d("Error", items.loadState.toString())
    LazyVerticalGrid(
        contentPadding = PaddingValues(vertical = 60.dp)
        ,
        modifier = modifier
            .fillMaxSize(),
        cells = GridCells.Fixed(3),
        content = {
            items(items.itemCount) { index ->
                val drug = items[index]
                if (drug != null) {
                    DrugsCardItem(drugImageUrl = drug.image, drugName = drug.drug_name) {

                    }
                }
            }

            items.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {}
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                        item {}
                    }
                    loadState.append is LoadState.Loading -> {
                        item {}
                        item { LoadingView() }
                        item {}
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = items.loadState.refresh as LoadState.Error
                        item {}
                        item {
                            ErrorView(
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() },
                                message = "No internet connection"
                            )
                        }
                        item {}
                    }
                    loadState.append is LoadState.Error -> {
                        item {}
                        item {
                            ErrorView(
                                onClickRetry = {
                                    retry()
                                    Log.d("click", "clicked")
                                },
                                message = null
                            )
                        }
                        item {}
                    }

                }
            }
        }
    )


}



