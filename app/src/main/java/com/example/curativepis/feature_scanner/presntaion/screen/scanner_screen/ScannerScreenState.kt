package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen

import android.net.Uri

data class ScannerScreenState(
    val imageUri: Uri? = null,
    val isImagePackedFromGallery: Boolean = false,
)
