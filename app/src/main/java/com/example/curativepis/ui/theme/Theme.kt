package com.example.curativepis.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.FontWeight.Companion.Black

private val DarkColorPalette = darkColors(
    primary = babyBlue,
    primaryVariant = Teal300,
    secondary = white,
    background = LightGrey,
    onBackground = white,
    onPrimary = white,
    onSecondary = black,

)

private val LightColorPalette = lightColors(
    primary = babyBlue,
    primaryVariant = Purple700,
    secondary = white,
    background = LightGrey,
    onBackground = white,
    onPrimary = white,
    onSecondary = black




    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
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