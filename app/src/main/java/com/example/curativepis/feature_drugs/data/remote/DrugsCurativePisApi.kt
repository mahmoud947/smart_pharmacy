package com.example.curativepis.feature_drugs.data.remote

import com.example.curativepis.feature_drugs.data.remote.dto.DrugDto
import com.example.curativepis.feature_drugs.data.remote.dto.DrugsResponseDto
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DrugsCurativePisApi {
    @GET("drugs")
    suspend fun getDrugs(
        @Query("limit")
        limit: Int,
        @Query("page")
        page: Int,
    ): DrugsResponseDto

    @GET("drugs")
    suspend fun getDrugsByName(
        @Query("name")
        drugName:String,
    ):DrugsResponseDto

    @GET("drugs/{drugId}")
    suspend fun getDrugsById(
       @Path("drugId")
        drugId:String,
    ):DrugDto
}