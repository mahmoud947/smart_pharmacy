package com.example.curativepis.feature_drugs.domian.repository

import androidx.paging.PagingData
import com.example.curativepis.feature_drugs.domian.model.Drug
import kotlinx.coroutines.flow.Flow


interface DrugsRepository {
    suspend fun getDrugs(page:Int): Result<List<Drug>>
}
