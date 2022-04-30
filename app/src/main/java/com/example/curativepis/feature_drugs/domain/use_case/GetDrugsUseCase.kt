package com.example.curativepis.feature_drugs.domain.use_case

import androidx.paging.PagingData
import com.example.curativepis.feature_drugs.domain.model.Drug
import com.example.curativepis.feature_drugs.domain.repository.DrugsRepository
import kotlinx.coroutines.flow.Flow

class GetDrugsUseCase(
    private val repository: DrugsRepository,
) {
suspend operator fun invoke(): Flow<PagingData<Drug>>{
    return repository.getDrugs()
}
}