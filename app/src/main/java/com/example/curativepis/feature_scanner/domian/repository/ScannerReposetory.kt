package com.example.curativepis.feature_scanner.domian.repository

import com.example.curativepis.core.util.network.Resource
import com.example.curativepis.feature_scanner.domian.model.PostResponse
import okhttp3.MultipartBody

interface ScannerReposetory{
    suspend fun sendScannerResult(image:MultipartBody.Part):PostResponse
}