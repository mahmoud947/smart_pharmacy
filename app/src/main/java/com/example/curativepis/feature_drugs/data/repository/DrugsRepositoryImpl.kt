package com.example.curativepis.feature_drugs.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.curativepis.core.util.network.Constants
import com.example.curativepis.feature_drugs.data.mapper.toDrug
import com.example.curativepis.feature_drugs.data.remote.DrugsCurativePisApi
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DrugsRepositoryImpl @Inject constructor(
    private val api: DrugsCurativePisApi,
) : DrugsRepository {

    override suspend fun getDrugs(page: Int): Result<List<Drug>> {
        return try {
            val response = api.getDrugsT(
                limit = Constants.DRUGS_PAGE_SIZE,
                page = page).data
            Result.success(response.map { it.toDrug() })
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}