package com.example.curativepis.feature_scanner.data.remote.dto

data class ScannerResponseDto(
    val __v: Int?,
    val _id: String?,
    val active_ingredients: List<String>?,
    val drug_name: String?,
    val forms: List<ScannerResponseFormDto>?,
    val price: Double?,
    val status: String?,
    val strength: String?
)