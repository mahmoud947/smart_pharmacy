package com.example.curativepis.feature_scanner.data.remote

import com.example.curativepis.feature_scanner.domian.model.PostResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ScannerCurativePisApi {
    @Multipart
    @POST("upload")
    suspend fun sendScannerResult(
        @Part scannerResult: MultipartBody.Part
    ):PostResponse

}