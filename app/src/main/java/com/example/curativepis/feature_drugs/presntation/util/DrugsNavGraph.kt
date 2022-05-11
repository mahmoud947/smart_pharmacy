package com.example.curativepis.feature_drugs.presntation.util

import androidx.compose.material.ScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.curativepis.core.commn.Constants
import com.example.curativepis.core.presentation.screen.home_screen.util.HomeScreens
import com.example.curativepis.feature_drugs.presntation.screen.drug_detail.DrugDetailScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen.DrugScreen
import com.example.curativepis.feature_drugs.presntation.screen.drug_search_screen.SearchScreen

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.drugsNavGraph(
    scaffoldState: ScaffoldState,
    navController: NavController,
) {
    navigation(
        startDestination = HomeScreens.Drugs.route,
        route = Constants.DRUGS_NAVGRAPH_ROOT
    ) {
        composable(route = HomeScreens.Drugs.route) {
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