package com.example.curativepis.feature_drugs.data.repository

import com.example.curativepis.core.commn.Constants
import com.example.curativepis.feature_cart.data.remote.response.dto.CartDto
import com.example.curativepis.feature_drugs.data.mapper.toDrug
import com.example.curativepis.feature_drugs.data.remote.DrugsCurativePisApi
import com.example.curativepis.feature_drugs.data.remote.request.AddItemToCartReq
import com.example.curativepis.feature_drugs.data.remote.response.dto.DrugDto
import com.example.curativepis.feature_drugs.domian.model.Drug
import com.example.curativepis.feature_drugs.domian.repository.DrugsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DrugsRepositoryImpl @Inject constructor(
    private val api: DrugsCurativePisApi,
) : DrugsRepository {

    override suspend fun getDrugs(page: Int): Result<List<Drug>> {
        return try {
            val response = api.getDrugs(
                limit = Constants.DRUGS_PAGE_SIZE,
                page = page).data
            Result.success(response.map { it.toDrug() })
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }

    override suspend fun getDrugsByName(drugName: String): List<DrugDto> {
        return api.getDrugsByName(drugName = drugName).data
    }

    override suspend fun getDrugsById(drugId: String): DrugDto {
       return api.getDrugsById(drugId = drugId)
    }

    override suspend fun addItemToCart(token: String,addItemToCartReq: AddItemToCartReq): CartDto? =
        api.addItemToCart(token = token, addItemToCartReq = addItemToCartReq)
}