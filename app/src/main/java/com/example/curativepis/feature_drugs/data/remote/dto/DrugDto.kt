package com.example.curativepis.feature_drugs.data.remote.dto

data class DrugDto(
    val _id: String?,
    val active_ingredients: List<String>?,
    val drug_name: String?,
    val forms: List<FormDto>?,
    val price: Double?,
    val status: String?,
    val strength: String?
)