package com.example.weather.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.ui.model.ExtendedWeatherUi
import com.example.weather.ui.model.WeatherExtendedDataUi

@ExperimentalAnimationApi
@Composable
fun ExtendedWeather(extendedWeatherUI: ExtendedWeatherUi) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        extendedWeatherUI.list.forEach {
            item { WeatherItem(weatherExtendedDataUi = it) }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun WeatherItem(weatherExtendedDataUi: WeatherExtendedDataUi) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .clickable(onClick = {
                isExpanded = !isExpanded
            })
            .padding(5.dp)
    ) {

        WeatherItemMainBox(weatherExtendedDataUi)

        AnimatedVisibility(visible = isExpanded) {
            Column {
                Divider(modifier = Modifier.padding(vertical = 10.dp))
                PHWRow(
                    pressure = weatherExtendedDataUi.main.pressure,
                    humidity = weatherExtendedDataUi.main.humidity,
                    windSpeed = weatherExtendedDataUi.wind.speed
                )
            }
        }
    }
}