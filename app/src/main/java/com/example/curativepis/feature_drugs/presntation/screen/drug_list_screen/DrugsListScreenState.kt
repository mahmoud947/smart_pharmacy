package com.example.curativepis.feature_drugs.presntation.screen.drug_list_screen

import androidx.paging.PagingData
import com.example.curativepis.feature_drugs.domain.model.Drug
import com.example.curativepis.feature_news.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

data class DrugsListScreenState(
   val drugs: Flow<PagingData<Drug>> = emptyFlow()
)

