package com.example.curativepis.feature_scanner.presntaion.util

sealed class Screen(val route:String){
    object CameraScreen:Screen("camera_screen")
}
