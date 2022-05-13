package com.example.curativepis.feature_drugs.presntation.util



sealed class DrugsScreens(val route:String){
    object SearchScreen:DrugsScreens(route = "search_screen")
    object DrugDetailScreen:DrugsScreens(route = "drug_detail_screen/{${DrugScreenArguments.DRUG_DETAIL_SCREEN_ARGUMENT_KEY}}"){
        fun passDrugId(drugID:String):String{
            return this.route.replace(oldValue ="{${DrugScreenArguments.DRUG_DETAIL_SCREEN_ARGUMENT_KEY}}", newValue = drugID )
        }
    }
}