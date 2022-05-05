package com.example.curativepis.feature_drugs.domian.use_case

import android.util.Log
import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_drugs.data.mapper.toDrug
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetDrugByIdUseCase(
    private val repository: DrugsRepository,
) {

    operator fun invoke(drugId: String): Flow<Resource<Drug>> = flow {
        try {
            emit(Resource.Loading<Drug>())
            val drug = repository.getDrugsById(drugId = drugId).toDrug()
            emit(Resource.Success<Drug>(data = drug))
        } catch (e: HttpException) {
            emit(Resource.Error<Drug>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Drug>(e.localizedMessage
                ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}