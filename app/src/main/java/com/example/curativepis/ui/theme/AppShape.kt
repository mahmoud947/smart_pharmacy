package com.example.curativepis.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppShape(
    val default: RoundedCornerShape = RoundedCornerShape(0.dp),
    val extraSmall: RoundedCornerShape = RoundedCornerShape(4.dp),
    val small: RoundedCornerShape = RoundedCornerShape(8.dp),
    val regulator: RoundedCornerShape = RoundedCornerShape(16.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(32.dp),
    val large: RoundedCornerShape = RoundedCornerShape(64.dp),
)

val LocalAppShape = compositionLocalOf { AppShape() }

val MaterialTheme.appShape: AppShape
    @Composable
    @ReadOnlyComposable
    get() = LocalAppShape.current
