package com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.curativepis.core.presentation.screen.main_screen.components.ErrorView
import com.example.curativepis.core.presentation.screen.main_screen.components.LoadingView
import com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.components.DrugSearchedItem
import com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.components.SearchAppBar
import com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.view_model.SearchScreenViewModel
import com.example.curativepis.feature_drugs.presntation.util.DrugsScreens
import com.example.curativepis.ui.theme.spacing
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchScreenViewModel = hiltViewModel(),
) {
    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val state = viewModel.uiState.value
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.secondary)) {
        SearchAppBar(text = viewModel.searchText.value,
            onTextChange = {
                viewModel.setSearchText(it)
                viewModel.getDrugsByName()
            },
            onCloseClicked = {
                navController.popBackStack()
            },
            onSearchClicked = {viewModel.getDrugsByName()},
            keyboardController = keyboardController,
            focusRequester = focusRequester,
        textFieldModifier = Modifier.focusRequester(focusRequester = focusRequester)
            .onFocusChanged {
                if (it.isFocused)
                    keyboardController?.show()
            })

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary)
                .weight(1f),
            contentPadding = PaddingValues(top = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.item) { drug ->
                DrugSearchedItem(drug = drug, onClick = {
                    navController.navigate(DrugsScreens.DrugDetailScreen.passDrugId(drugID = drug._id))
                    keyboardController?.hide()
                })
            }
            state.item.apply {
                when {
                    state.isLoading -> {
                        item {
                            LoadingView(modifier = Modifier.fillParentMaxSize())
                        }
                    }
                    !state.error.isNullOrEmpty() -> {
                        item {
                            ErrorView(onClickRetry = {
                                viewModel.getDrugsByName()
                            }, message = state.error)
                        }
                    }
                }
            }

        }
    }

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}

