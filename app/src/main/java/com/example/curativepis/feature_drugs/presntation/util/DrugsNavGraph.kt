package com.example.curativepis.feature_drugs.presntation.util

import androidx.compose.material.ScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.curativepis.core.navigation.BottomBarScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetailScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.DrugScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.SearchScreen

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.drugsNavGraph(
    scaffoldState: ScaffoldState,
    navController: NavController,
) {
    navigation(
        startDestination = BottomBarScreen.Drugs.route,
        route = "DRUGS_SCREEN"
    ) {
        composable(route = BottomBarScreen.Drugs.route) {
            DrugScreen(scaffoldState = scaffoldState, navController = navController)
        }
        composable(route = DrugsScreens.SearchScreen.route){
            SearchScreen(navController = navController)
        }
        composable(
            route = DrugsScreens.DrugDetailScreen.route,
            arguments = listOf(navArgument(DrugScreenArguments.DRUG_DETAIL_SCREEN_ARGUMENT_KEY){
                type= NavType.StringType
            })
        ){
            val drugID=it.arguments?.getString(DrugScreenArguments.DRUG_DETAIL_SCREEN_ARGUMENT_KEY)
            DrugDetailScreen(drugID = drugID, navController = navController)
        }
    }
}