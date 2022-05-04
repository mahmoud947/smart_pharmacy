package com.example.curativepis.feature_scanner.presntaion.screen.camera_screen

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.curativepis.feature_scanner.presntaion.screen.camera_screen.components.CameraView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center) {
        val systemUiController = rememberSystemUiController()
        val color = Color.Black


        val cameraPermissionState = rememberPermissionState(
            Manifest.permission.CAMERA
        )

        val context = LocalContext.current

        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(key1 = lifecycleOwner, effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    cameraPermissionState.launchPermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
        )

        SideEffect {
            systemUiController.setStatusBarColor(
                color = color,
                darkIcons = false
            )
        }

        when (cameraPermissionState.status) {
            is PermissionStatus.Granted -> {
                Toast.makeText(context, "camera is work", Toast.LENGTH_SHORT).show()
            }
            is PermissionStatus.Denied -> {
                Toast.makeText(context, "camera is not work", Toast.LENGTH_SHORT).show()
            }
        }

        CameraView(onImageCaptured = { uri, fromGallery ->


        }, onError = {

        }) {

        }

    }

}