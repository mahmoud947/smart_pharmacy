package com.example.curativepis

import android.app.Application
import com.example.curativepis.feature_scanner.domian.model.ScannerResponse
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CurativePisApplication:Application() {
    var item:List<ScannerResponse> = emptyList()
}