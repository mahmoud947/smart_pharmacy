package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen

import androidx.paging.PagingData
import com.example.curativepis.feature_drugs.domian.model.Drug
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class DrugsListScreenState(
   val isLoading: Boolean = false,
   val item: List<Drug> = emptyList(),
   val error: String? = null,
   val endReached: Boolean = false,
   val page: Int = 0,
)

