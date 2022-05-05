package com.example.curativepis.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 2.dp,
    val small: Dp = 4.dp,
    val regulator: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
    val xLarge:Dp=86.dp,
    val bottomNavigationBar: Dp = 60.dp,
    val toolbarHeight:Dp=48.dp,
    val largeButtonH:Dp=50.dp,
    val smallButtonH:Dp=50.dp,
    val largeButtonX:Dp=500.dp,
    val smallButtonX:Dp=250.dp,
    val defaultDivider:Dp=80.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current