package com.example.curativepis.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.FontWeight.Companion.Black

private val DarkColorPalette = darkColors(
    primary = darkGrey400,
    secondary = darkGrey400,
    primaryVariant = babyBlue,
    background = black,
    onBackground = whiteSmoke,
    onPrimary = whiteSmoke,
    onSecondary = whiteSmoke,
    error = read,
    surface = darkGrey300,
    onError = white,
    onSurface = whiteSmoke,
    secondaryVariant = babyBlue,
)

private val LightColorPalette = lightColors(
    primary = babyBlue,
    onPrimary = whiteSmoke,
    secondary = babyBlue,
    onSecondary = whiteSmoke,
    background = whiteSmoke,
    onBackground = darkGrey,
    surface = white,
    onSurface = darkGrey200,
    error = read,
    onError = white,
    primaryVariant = babyBlue,
)

@Composable
fun CurativePISTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalElevation provides  Elevation(),
        LocalAppShape provides  AppShape(),
    ) {

    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}