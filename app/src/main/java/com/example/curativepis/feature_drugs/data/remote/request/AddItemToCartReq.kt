package com.example.curativepis.feature_drugs.data.remote.request

data class AddItemToCartReq(
    val drugId: String,
    val quantity: Int
)