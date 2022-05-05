package com.example.curativepis.feature_drugs.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DrugDto(
    val _id: String?,
    val active_ingredients: List<String>?,
    val drug_name: String?,
    @SerializedName("forms")
    val drugFormsDto: List<DrugFormDto>?,
    val price: Double?,
    val status: String?,
    val strength: String?
)