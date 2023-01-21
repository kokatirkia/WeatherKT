package com.example.weather.ui.screens.commoncomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessage(fetchWeatherData: () -> Unit, errorMessage: String?) {
    if (errorMessage.isNullOrEmpty()) return
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .padding(10.dp)
    ) {
        Text(
            text = errorMessage,
            modifier = Modifier
                .padding(10.dp)
                .testTag("errorTestTag"),
            color = Color.White
        )
        IconButton(onClick = fetchWeatherData) {
            Icon(
                Icons.Rounded.Refresh,
                contentDescription = "refreshIconButton",
                tint = Color.White
            )
        }
    }
}