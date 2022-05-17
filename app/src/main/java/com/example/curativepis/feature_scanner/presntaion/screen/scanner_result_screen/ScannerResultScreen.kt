package com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen

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
import com.example.curativepis.RunTimeCash
import com.example.curativepis.core.presentation.components.DefaultTopAppBar
import com.example.curativepis.feature_cart.presntation.cart_screen.components.CartItemCard
import com.example.curativepis.feature_scanner.domian.model.ScannerResponse
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen.components.ScannerResultItemCard
import com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen.view_model.ScannerScreenViewModel
import com.example.curativepis.ui.theme.spacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScannerResultScreen(
    scaffoldState: ScaffoldState,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val item=RunTimeCash.arr

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {

        DefaultTopAppBar(onClick = {
            scope.launch {
                scaffoldState.drawerState.open()
                Toast.makeText(context,item.toString(),Toast.LENGTH_LONG).show()
            }
        }, title = "Scanner Result")

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = MaterialTheme.spacing.large),
        ) {
            items(items = item) { item ->
                ScannerResultItemCard(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.small),
                    scannerResponse = item,
                    onAddToCartIconClicked = {

                    },
                    onClick = {

                    }
                )
            }

        }
    }
}


