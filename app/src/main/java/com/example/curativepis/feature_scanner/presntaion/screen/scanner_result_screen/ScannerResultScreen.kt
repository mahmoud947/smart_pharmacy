package com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen

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
import com.example.curativepis.RunTimeCash
import com.example.curativepis.core.presentation.components.DefaultTopAppBar
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen.components.ScannerResultItemCard
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen.view_model.ScannerResultScreenlViewModel
import com.example.curativepis.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScannerResultScreen(
    scaffoldState: ScaffoldState,
    viewModel: ScannerResultScreenlViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val itemCashed=RunTimeCash.arr
    val drugs = remember { mutableStateOf(itemCashed) }
    val state=viewModel.uiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {

        DefaultTopAppBar(onClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }, title = "Scanner Result")

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = MaterialTheme.spacing.large),
        ) {
            items(items = drugs.value) { item ->
                ScannerResultItemCard(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.small),
                    scannerResponse = item,
                    onAddToCartIconClicked = {
                        viewModel.onEvent(ScannerResultScreenEvent.AddItemToCart(drugId = item._id))

                        if (state.isItemAddToCart){
                            drugs.value=drugs.value.filter { it._id!=item._id }
                        }
                    },
                    onClick = {

                    }
                )
            }

        }
    }
}


