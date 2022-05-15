package com.example.curativepis.feature_drugs.presntation.screen.drug_detail

sealed class DrugDetaillScreenEvent{
    data class AddItemToCart(val drugId:String,val quantity:Int):DrugDetaillScreenEvent()
}
