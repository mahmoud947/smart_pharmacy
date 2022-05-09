package com.example.curativepis.feature_scanner.presntaion.screen.scanner_screen

import android.net.Uri

sealed class ScannerScreenEvent{
    data class PickImageFromGellary(val imageUri: Uri?):ScannerScreenEvent()
    data class UploadImage(val filePath:String):ScannerScreenEvent()
}
