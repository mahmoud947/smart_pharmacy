package com.example.curativepis.feature_drugs.domain.model

data class Drug(
    val _id: String,
    val image: String,
    val drug_name: String,
    val price: Double,
    val strength: String,
)
