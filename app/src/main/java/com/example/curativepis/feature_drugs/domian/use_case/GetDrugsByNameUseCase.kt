package com.example.curativepis.feature_drugs.domian.use_case

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_drugs.data.mapper.toDrug
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetDrugsByNameUseCase(
    private val repository: DrugsRepository,
) {
    operator fun invoke(drugName: String): Flow<Resource<List<Drug>>> = flow {
        try {
            emit(Resource.Loading<List<Drug>>())
            val drugs = repository.getDrugsByName(drugName = drugName).map { it.toDrug() }
            emit(Resource.Success<List<Drug>>(drugs))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Drug>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Drug>>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}