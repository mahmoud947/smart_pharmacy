package com.example.curativepis.feature_scanner.data.remote

import com.example.curativepis.feature_drugs.data.remote.dto.DrugsResponseDto
import okhttp3.MultipartBody
import retrofit2.http.*

interface ScannerCurativePisApi {
    @Multipart
    @POST("api/img")
    suspend fun sendScannerResult(
        @Part scannerResult: MultipartBody.Part
    ):Any

}