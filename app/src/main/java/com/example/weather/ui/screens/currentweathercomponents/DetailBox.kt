package com.example.weather.ui.screens.currentweathercomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.ui.screens.commoncomponents.PHWRow
import com.example.weather.ui.model.CurrentWeatherUi

@Composable
fun DetailBox(currentWeatherState: CurrentWeatherUi) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(20.dp)
        ) {
            SunriseSunsetRow(
                currentWeatherState.sys.sunrise,
                currentWeatherState.sys.sunset
            )
            Spacer(modifier = Modifier.padding(vertical = 15.dp))
            PHWRow(
                currentWeatherState.main.pressure,
                currentWeatherState.main.humidity,
                currentWeatherState.wind.speed
            )
        }
    }
}