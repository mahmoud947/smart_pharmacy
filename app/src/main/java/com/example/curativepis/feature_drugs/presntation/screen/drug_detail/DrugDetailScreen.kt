package com.example.curativepis.feature_drugs.presntation.screen.drug_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.curativepis.R
import com.example.curativepis.core.presentation.screen.main_screen.components.DefaultTopAppBar
import com.example.curativepis.core.presentation.screen.main_screen.components.ErrorView
import com.example.curativepis.core.presentation.screen.main_screen.components.LoadingView
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.components.DrugFormItem
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.components.TitleAndValueText
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.view_model.DrugDetailViewModel
import com.example.curativepis.ui.theme.elevation
import com.example.curativepis.ui.theme.spacing

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DrugDetailScreen(
    drugID: String?,
    navController: NavController,
    viewModel: DrugDetailViewModel = hiltViewModel(),
) {
    if (drugID != null) {
        LaunchedEffect(key1 = drugID) {
            viewModel.getDrugDetail(drugID = drugID)
        }
    }
    val state = viewModel.uiState.value
    val scrollState = rememberScrollState()
    val drug = state.drug
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.Start
    ) {
        DefaultTopAppBar(
            navigationIcon = Icons.Filled.ArrowBack,
            onClick = {
                navController.popBackStack()
            }
        )

        when {
            !state.error.isNullOrEmpty() -> {
                ErrorView(onClickRetry = {
                    if (drugID != null) {
                        viewModel.getDrugDetail(drugID = drugID)
                    }
                },
                    message = state.error,
                    modifier = Modifier.fillMaxSize())
            }
            state.isLoading -> {
                LoadingView(modifier = Modifier.fillMaxSize())
            }
            state.drug != null -> {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background),
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)) {
                        val painter = rememberImagePainter(data = drug?.image, builder = {
                            placeholder(R.drawable.loading_waiting)
                            error(R.drawable.error_drug_image)
                        })
                        Image(painter = painter,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop)
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            if (drug != null) {
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                                TitleAndValueText(title = "Drug name", value = drug.drug_name)
                            }
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                            Box(modifier = Modifier.fillMaxSize()) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically) {
                                    if (drug != null) {
                                        TitleAndValueText(title = "Price",
                                            value = "${drug.price} EL",
                                            modifier = Modifier.weight(2f))
                                        Box(modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f)
                                        ) {
                                            Surface(
                                                modifier = Modifier
                                                    .padding(12.dp),
                                                elevation = MaterialTheme.elevation.regulator,
                                                color = MaterialTheme.colors.primaryVariant,
                                                shape = MaterialTheme.shapes.large
                                            ) {

                                                IconButton(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    onClick = { /*TODO*/ })
                                                {
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxSize(),
                                                        verticalArrangement = Arrangement.Center,
                                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                                        Icon(imageVector = Icons.Default.AddShoppingCart,
                                                            contentDescription = null,
                                                            tint = MaterialTheme.colors.onPrimary)
                                                        Text(
                                                            text = "Add to Cart",
                                                            style = MaterialTheme.typography.caption.copy(
                                                                color = MaterialTheme.colors.onPrimary,
                                                                textAlign = TextAlign.Center),
                                                            maxLines = 1,
                                                            modifier = Modifier.fillMaxWidth(),
                                                        )
                                                    }

                                                }


                                            }


                                        }
                                    }

                                }

                            }
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                            TitleAndValueText(title = "Forms", value = null)
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                                contentPadding = PaddingValues(MaterialTheme.spacing.large)
                            ) {
                                if (drug != null) {
                                    items(items = drug.drugForm) { drugForm ->
                                        DrugFormItem(drugForm = drugForm,
                                            modifier = Modifier.width(100.dp))
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                            if (drug != null) {
                                TitleAndValueText(title = "Strength", value = drug.strength)
                            }
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                            TitleAndValueText(title = "Active ingredients", value = null)
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                                horizontalAlignment = Alignment.Start,
                                contentPadding = PaddingValues(start = MaterialTheme.spacing.large)
                            ) {
                                val activeIngredients = drug!!.active_ingredients
                                items(items = activeIngredients) { activeIngredient ->
                                    Text(text = "${activeIngredients.indexOf(activeIngredient) + 1}. " +
                                            "$activeIngredient",
                                        style = MaterialTheme.typography.body1.copy(color = Color.Black))
                                }
                            }
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                        }
                    }

                }


            }
            else -> {
                ErrorView(onClickRetry = {
                    if (drugID != null) {
                        viewModel.getDrugDetail(drugID = drugID)
                    }
                }, message = state.error, modifier = Modifier.fillMaxSize())
            }
        }


    }

}