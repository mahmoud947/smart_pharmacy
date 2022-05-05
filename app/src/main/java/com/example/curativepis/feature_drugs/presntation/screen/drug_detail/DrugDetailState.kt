package com.example.curativepis.feature_drugs.presntation.screen.drug_detail

import com.example.curativepis.feature_drugs.domian.model.Drug

data class DrugDetailState(
    val isLoading: Boolean = false,
    val drug: Drug? = null,
    val error: String? = null,
)
