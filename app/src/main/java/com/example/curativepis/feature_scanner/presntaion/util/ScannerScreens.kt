package com.example.curativepis.feature_scanner.presntaion.util

sealed class ScannerScreens(val route:String){
    object CameraScreen:ScannerScreens("camera_screen")
}
