package com.example.curativepis.feature_scanner.presntaion.util

const val SCANNERRESULTKEY="scanner_result_key"
sealed class ScannerScreens(val route:String){
    object CameraScreen:ScannerScreens("camera_screen")
    object ScannerResultScreen:ScannerScreens("scanner_result_screen")
}
