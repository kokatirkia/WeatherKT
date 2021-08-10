package com.example.weather.ui.components

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
import androidx.compose.ui.unit.dp
import com.example.weather.ui.WeatherViewModel

@Composable
fun NoInternetConnection(weatherViewModel: WeatherViewModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .padding(10.dp)
    ) {
        Text(
            text = "No internet Connection!",
            modifier = Modifier.padding(10.dp),
            color = Color.White
        )
        IconButton(onClick = {
            weatherViewModel.fetchWeatherData()
        }) {
            Icon(
                Icons.Rounded.Refresh,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}