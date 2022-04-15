package com.example.curativepis.feature_drugs.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.curativepis.feature_drugs.data.paging.DrugsPagingSource
import com.example.curativepis.feature_drugs.data.remote.CurativePisApi
import com.example.curativepis.feature_drugs.domain.model.Drug
import com.example.curativepis.feature_drugs.domain.repository.DrugsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DrugsRepositoryImpl @Inject constructor(
    private val api: CurativePisApi,
) : DrugsRepository {
    override suspend fun getDrugs(): Flow<PagingData<Drug>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                DrugsPagingSource(api = api)
            }
        ).flow
    }
}