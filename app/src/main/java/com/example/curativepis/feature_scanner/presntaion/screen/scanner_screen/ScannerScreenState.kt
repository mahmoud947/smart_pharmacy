package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen

import android.net.Uri
import com.example.curativepis.feature_scanner.domian.model.PostResponse

data class ScannerScreenState(
    val imageUri: Uri? = null,
    val isButtonEnable: Boolean = false,
    val isLoding: Boolean = false,
    val error: String? = null,
    val drugs:PostResponse?=null
)
