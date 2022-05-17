package com.example.curativepis.feature_scanner.domian.model

data class ScannerResponse(
    val __v: Int,
    val _id: String,
    val active_ingredients: List<String>,
    val drug_name: String,
    val forms: List<ScannerResponseForm>,
    val price: Double,
    val status: String,
    val strength: String
)