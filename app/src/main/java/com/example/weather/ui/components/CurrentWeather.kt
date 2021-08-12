package com.example.weather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.weather.ui.model.CurrentWeatherUi
import com.example.weather.utils.Constants

@Composable
fun CurrentWeather(currentWeatherState: CurrentWeatherUi) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = currentWeatherState.name,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = currentWeatherState.main.temp,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = currentWeatherState.main.feelsLike,
            fontSize = 20.sp,
            color = Color.White
        )
        Text(
            text = currentWeatherState.weather[0].description,
            fontSize = 20.sp,
            color = Color.White
        )
        Image(
            painter = rememberImagePainter(
                data = Constants.iconUrl + currentWeatherState.weather[0].icon,
                builder = { crossfade(true) }
            ),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        DetailBox(currentWeatherState)
    }
}