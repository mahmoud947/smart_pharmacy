package com.example.curativepis.feature_drugs.data.remote

import com.example.curativepis.feature_cart.data.remote.response.dto.CartDto
import com.example.curativepis.feature_drugs.data.remote.request.AddItemToCartReq
import com.example.curativepis.feature_drugs.data.remote.response.dto.DrugDto
import com.example.curativepis.feature_drugs.data.remote.response.dto.DrugsResponseDto
import retrofit2.http.*

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

    @PATCH("cart")
    suspend fun addItemToCart(
        @Header("Authorization") token: String,
        @Body addItemToCartReq: AddItemToCartReq
    ): CartDto?
}