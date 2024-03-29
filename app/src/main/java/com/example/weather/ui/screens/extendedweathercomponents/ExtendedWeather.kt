package com.example.weather.ui.screens.extendedweathercomponents

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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.weather.ui.model.ExtendedWeatherUi

@ExperimentalAnimationApi
@Composable
fun ExtendedWeather(extendedWeatherUI: ExtendedWeatherUi) {
    LazyColumn(
        modifier = Modifier.semantics { contentDescription = "extendedWeatherList" },
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        extendedWeatherUI.list.forEach {
            item { WeatherItem(weatherItem = it) }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun WeatherItem(weatherItem: ExtendedWeatherUi.WeatherItem) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .semantics { contentDescription = "weatherItem" }
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

        WeatherItemMainBox(weatherItem)

        AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.semantics { contentDescription = "expandableColumn" }) {
                Divider(modifier = Modifier.padding(vertical = 10.dp))
                PHWRow(
                    pressure = weatherItem.main.pressure,
                    humidity = weatherItem.main.humidity,
                    windSpeed = weatherItem.wind.speed
                )
            }
        }
    }
}