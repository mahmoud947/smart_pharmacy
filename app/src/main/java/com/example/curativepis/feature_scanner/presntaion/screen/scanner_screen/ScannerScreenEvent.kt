package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen

import android.net.Uri

sealed class ScannerScreenEvent{
    data class SelectImageFromGellary(val imageUri: Uri):ScannerScreenEvent()
}
