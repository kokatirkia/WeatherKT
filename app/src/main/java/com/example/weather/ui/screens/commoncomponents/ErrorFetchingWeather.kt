package com.example.weather.ui.screens.commoncomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.R

@Composable
fun ErrorFetchingWeather(message: String?, fetchWeatherData: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = message ?: "",
                color = Color.White,
            )
            Spacer(modifier = Modifier.padding(horizontal = 7.dp))
            Image(
                painterResource(R.drawable.ic_404_error),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
        IconButton(onClick = fetchWeatherData) {
            Icon(
                Icons.Rounded.Refresh,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}