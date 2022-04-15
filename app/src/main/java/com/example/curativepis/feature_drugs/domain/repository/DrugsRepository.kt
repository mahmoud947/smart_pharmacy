package com.example.curativepis.feature_drugs.domain.repository

import androidx.paging.PagingData
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_drugs.data.remote.dto.DrugDto
import com.example.curativepis.feature_drugs.data.remote.dto.DrugsResponseDto
import com.example.curativepis.feature_drugs.domain.model.Drug
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface DrugsRepository {
    suspend fun getDrugs(): Flow<PagingData<Drug>>
}
