package com.example.curativepis.feature_scanner.presntaion.screen.scanner_result_screen

sealed class ScannerResultScreenEvent{
    data class AddItemToCart(val drugId:String):ScannerResultScreenEvent()
}
