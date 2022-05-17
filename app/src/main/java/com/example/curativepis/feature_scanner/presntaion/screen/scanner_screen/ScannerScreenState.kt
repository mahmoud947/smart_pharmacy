package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen

import android.net.Uri
import com.example.curativepis.feature_scanner.domian.model.PostResponse
import com.example.curativepis.feature_scanner.domian.model.ScannerResponse

data class ScannerScreenState(
    val imageUri: Uri? = null,
    val isButtonEnable: Boolean = false,
    val isLoding: Boolean = false,
    val error: String? = null,
    val scannerresponse:List<ScannerResponse> = emptyList()
)
