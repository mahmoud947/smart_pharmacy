package com.example.curativepis.feature_drugs.data.remote

import com.example.curativepis.feature_drugs.data.remote.dto.DrugsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurativePisApi {
    @GET("drugs")
    suspend fun getDrugs(
        @Query("offset")
        offset: Int,
        @Query("limit")
        limit: Int,
        @Query("page")
        page: Int,
    ): DrugsResponseDto

}