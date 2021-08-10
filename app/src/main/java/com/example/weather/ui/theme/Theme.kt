package com.example.weather.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val colorPalette = darkColors(
    primary = lightGrey,
    primaryVariant = darkGrey,
    secondary = grey,
    background = darkGrey,
    surface = darkGrey
)

@Composable
fun WeatherTheme(content: @Composable() () -> Unit) {
    val colors = colorPalette

    MaterialTheme(
        colors = colors,
        content = content
    )
}