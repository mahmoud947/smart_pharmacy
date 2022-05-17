package com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen

import com.example.curativepis.feature_drugs.domian.model.Drug

data class ScannerResultState(
    val isLoading: Boolean = false,
    val drug: Drug? = null,
    val error: String? = null,
    val isItemAddToCart: Boolean = false,
    val itemAddMessage: String? = null,
)
