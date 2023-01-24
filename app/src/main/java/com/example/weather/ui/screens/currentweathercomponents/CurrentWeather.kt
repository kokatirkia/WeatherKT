package com.example.weather.ui.screens.currentweathercomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.weather.ui.model.CurrentWeatherUi
import com.example.weather.utils.Constants

@Composable
fun CurrentWeather(currentWeatherUi: CurrentWeatherUi) {
    Column(
        modifier = Modifier
            .semantics { contentDescription = "currentWeatherColumn" }
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = currentWeatherUi.name,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Row {
            Image(
                painter = rememberImagePainter(
                    data = Constants.iconUrl + currentWeatherUi.weather[0].icon,
                    builder = { crossfade(true) }
                ),
                contentDescription = "weatherIcon",
                modifier = Modifier.size(50.dp)
            )
            Column {
                Text(
                    text = currentWeatherUi.day,
                    color = Color.White
                )
                Text(
                    text = currentWeatherUi.time,
                    color = Color.White
                )
            }
        }
        Text(
            text = currentWeatherUi.main.temp,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = currentWeatherUi.weather[0].description,
            fontSize = 18.sp,
            color = Color.White
        )
        Text(
            text = currentWeatherUi.main.feelsLike,
            color = Color.White
        )
        DetailBox(currentWeatherUi)
    }
}