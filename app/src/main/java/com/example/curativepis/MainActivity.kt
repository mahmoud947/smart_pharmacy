package com.example.curativepis

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.curativepis.core.presentation.screen.home_screen.HomeScreen
import com.example.curativepis.ui.theme.CurativePISTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurativePISTheme {
                val navController = rememberNavController()
                HomeScreen(navController = navController)
            }
        }
    }
}


