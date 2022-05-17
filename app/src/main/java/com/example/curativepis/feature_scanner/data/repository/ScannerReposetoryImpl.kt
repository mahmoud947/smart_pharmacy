package com.example.curativepis.feature_scanner.data.repository

import com.example.curativepis.feature_scanner.data.remote.ScannerCurativePisApi
import com.example.curativepis.feature_scanner.data.remote.dto.ScannerResponseDto
import com.example.curativepis.feature_scanner.domian.model.PostResponse
import com.example.curativepis.feature_scanner.domian.repository.ScannerReposetory
import okhttp3.MultipartBody
import javax.inject.Inject

class ScannerReposetoryImpl @Inject constructor(
    private val api:ScannerCurativePisApi
) : ScannerReposetory {
    override suspend fun sendScannerResult(image: MultipartBody.Part):PostResponse=
        api.sendScannerResult(scannerResult = image)

    override suspend fun getScannerResponse(image: MultipartBody.Part): List<ScannerResponseDto> =
        api.getScannserResponse(image = image)

}