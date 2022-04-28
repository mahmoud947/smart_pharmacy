package com.example.curativepis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.curativepis.core.presentation.screen.main_screen.MainScreen
import com.example.curativepis.ui.theme.CurativePISTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurativePISTheme {
                MainScreen()
            }
        }
    }
}

